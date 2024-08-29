package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.Message
import com.genflowly.aicallerlib.models.OpenAIChatCreateRequest
import com.genflowly.aicallerlib.models.OpenAIChatCreateResponse
import com.genflowly.aicallerlib.models.Role
import com.genflowly.aicallerlib.utils.Constants.OPENAI_BASE_API
import com.genflowly.aicallerlib.utils.Constants.OPENAI_CHAT_ENDPOINT
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class ApiClient : KoinComponent {
    private val httpClient: HttpClient by inject()
    private val openAIKey: String by inject(named("openAIKey"))
    private val model: String by inject(named("model"))
    private val maxTokens: Int by inject(named("maxTokens"))
    private val temperature: Double by inject(named("temperature"))
    private val topP: Double by inject(named("topP"))

    suspend fun callOpenAI(prompt: String): String {

        val requestBody = OpenAIChatCreateRequest(
            model = model,
            messages = listOf(Message(role = Role.USER, content = prompt)),
            maxTokens = maxTokens,
            temperature = temperature,
            topP = topP
        )

        val response: HttpResponse = httpClient.post("$OPENAI_BASE_API$OPENAI_CHAT_ENDPOINT") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $openAIKey")
            }
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestBody))
        }

        // Log the raw response body for debugging
        val rawResponseBody = response.bodyAsText()
        println("Raw Response: $rawResponseBody")

        // Check if there's an error in the response and handle it
        if (response.status != HttpStatusCode.OK) {
            throw Exception("API call failed with status: ${response.status}, body: $rawResponseBody")
        }

        val responseBody: OpenAIChatCreateResponse = Json.decodeFromString(rawResponseBody)
        return responseBody.choices.firstOrNull()?.message?.content?.trim() ?: "No response"
    }
}
