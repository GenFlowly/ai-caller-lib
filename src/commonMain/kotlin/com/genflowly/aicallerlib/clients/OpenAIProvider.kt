package com.genflowly.aicallerlib.provider

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

class OpenAIProvider(private val httpClient: HttpClient) : AIProvider {
    override suspend fun generateResponse(apiKey: String, config: AIRequestConfig): String {
        val requestBody = OpenAIChatCreateRequest(
            model = config.model,
            messages = listOf(OpenAIMessageRequest(role = config.role, content = config.content)),
            maxTokens = config.maxOutputTokens,
            temperature = config.temperature,
            topP = config.topP
        )

        val response: HttpResponse = httpClient.post("$OPENAI_BASE_API$OPENAI_CHAT_ENDPOINT") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $apiKey")
            }
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestBody))
        }

        val responseBody: OpenAIChatCreateResponse = Json.decodeFromString(response.bodyAsText())
        return responseBody.choices.firstOrNull()?.message?.content?.trim() ?: "No response"
    }
}