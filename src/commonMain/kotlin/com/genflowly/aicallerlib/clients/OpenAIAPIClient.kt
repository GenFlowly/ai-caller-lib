package com.genflowly.aicallerlib.clients

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ApiClient(
    private val httpClient: HttpClient,
    private val openAIKey: String,
    private val model: String,
    private val maxTokens: Int,
    private val temperature: Double,
    private val topP: Double
) {

    private val openAIEndpoint = "https://api.openai.com/v1/completions"

    suspend fun callOpenAI(prompt: String): String {
        val requestBody = OpenAIRequest(
            model = model,
            prompt = prompt,
            max_tokens = maxTokens,
            temperature = temperature,
            top_p = topP
        )

        val response: HttpResponse = httpClient.post(openAIEndpoint) {
            headers {
                append(HttpHeaders.Authorization, "Bearer $openAIKey")
            }
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(requestBody))
        }

        val responseBody: OpenAIResponse = response.body()
        return responseBody.choices.firstOrNull()?.text?.trim() ?: "No response"
    }
}

@Serializable
data class OpenAIRequest(
    val model: String,
    val prompt: String,
    val max_tokens: Int,
    val temperature: Double,
    val top_p: Double
)

@Serializable
data class OpenAIResponse(
    val choices: List<Choice>
)

@Serializable
data class Choice(
    val text: String
)
