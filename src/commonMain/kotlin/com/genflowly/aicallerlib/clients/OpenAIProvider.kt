package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequestConfig
import com.genflowly.aicallerlib.models.openai.OpenAIChatCreateRequest
import com.genflowly.aicallerlib.models.openai.OpenAIChatCreateResponse
import com.genflowly.aicallerlib.models.openai.OpenAIMessageRequest
import com.genflowly.aicallerlib.utils.Constants.OPENAI_BASE_API
import com.genflowly.aicallerlib.utils.Constants.OPENAI_CHAT_ENDPOINT
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mu.KLogger
import java.io.IOException

class OpenAIProvider(
    private val httpClient: HttpClient,
    private val logger: KLogger,
    private val json: Json
) :
    AIProvider<OpenAIChatCreateResponse> {
    override suspend fun generateResponse(apiKey: String, config: AIRequestConfig): OpenAIChatCreateResponse {
        return try {
            val requestBody = OpenAIChatCreateRequest(
                model = config.model,
                messages = listOf(
                    OpenAIMessageRequest(
                        role = config.role,
                        content = config.content
                    )
                ),
                maxTokens = config.maxOutputTokens,
                temperature = config.temperature,
                topP = config.topP
            )

            logger.info { "Sending request to OpenAI with model: ${config.model}, prompt: ${config.content}" }

            val response: HttpResponse = httpClient.post("$OPENAI_BASE_API$OPENAI_CHAT_ENDPOINT") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $apiKey")
                }
                contentType(ContentType.Application.Json)
                setBody(json.encodeToString(requestBody))
            }

            if (response.status != HttpStatusCode.OK) {
                throw IOException("Failed to call OpenAI API: ${response.status}, ${response.bodyAsText()}")
            }
            val responseBody: OpenAIChatCreateResponse =
                json.decodeFromString(response.bodyAsText())
            logger.info { "Received response from OpenAI: ${responseBody.choices.firstOrNull()?.message?.content?.trim()}" }
            logger.info { "Received usage from OpenAI: ${responseBody.usage}" }
            logger.info { "Received id from OpenAI: ${responseBody.id}" }
            responseBody

        } catch (e: IOException) {
            logger.error(e) { "IO Exception occurred: ${e.message}" }
            throw e
        } catch (e: Exception) {
            logger.error(e) { "An unexpected error occurred: ${e.message}" }
            throw e
        }
    }
}
