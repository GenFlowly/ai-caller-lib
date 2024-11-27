package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequestConfig
import com.genflowly.aicallerlib.models.Role
import com.genflowly.aicallerlib.models.openai.OpenAIChatCreateResponse
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.errors.*
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import mu.KLogger
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OpenAIProviderTest {

    private lateinit var openAIProvider: OpenAIProvider
    private val logger: KLogger = mockk(relaxed = true)
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val choices =
        """[{"index": 0,"message": {"role": "assistant","content": "Test response","refusal": null},
            |"logprobs": null,"finish_reason": "stop"}]""".trimMargin()

    @BeforeTest
    fun setUp() {
        val mockEngine = MockEngine { _ ->
            respond(
                content = """{"id":"chatcmpl-A38WmhyDLMG0HbMQzNawmaN3afmR4",
                    |"model":"gpt-3.5-turbo-0125","created": 1725311128,"object": "chat.completion",
                    |"choices":$choices}""".trimMargin(),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                )
            )
        }

        val httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(json) // Use the configured Json instance
            }
        }
        openAIProvider = OpenAIProvider(httpClient, logger, json)
    }

    @Test
    fun `test generateResponse success`() = runBlocking {
        val config = AIRequestConfig(
            model = "gpt-3.5-turbo-0125",
            role = Role.USER,
            content = "Hello, OpenAI!",
            maxOutputTokens = 100,
            temperature = 0.5,
            topP = 1.0
        )

        val response = openAIProvider.generateResponse("fake-api-key", config) as? OpenAIChatCreateResponse
        if (response is OpenAIChatCreateResponse) {
            assertEquals("Test response", response.choices.firstOrNull()?.message?.content?.trim())
        } else {
            // Handle the unexpected type
            throw IllegalStateException("Response is not of type OpenAIChatCreateResponse")
        }
    }

    @Test
    fun `test generateResponse failure`() = runBlocking {
        val mockEngine = MockEngine { request ->
            respond(
                content = """{"error":"Invalid request"}""",
                status = HttpStatusCode.BadRequest,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                )
            )
        }

        val httpClient = HttpClient(mockEngine)
        val failingProvider = OpenAIProvider(httpClient, logger, json)

        val config = AIRequestConfig(
            model = "text-davinci-003",
            role = Role.USER,
            content = "Hello, OpenAI!",
            maxOutputTokens = 100,
            temperature = 0.5,
            topP = 1.0
        )

        assertFailsWith<IOException> {
            runBlocking {
                failingProvider.generateResponse("fake-api-key", config)
            }
        }
    }
}
