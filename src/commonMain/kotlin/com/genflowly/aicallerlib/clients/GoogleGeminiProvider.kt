package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequestConfig
import com.genflowly.aicallerlib.models.gemini.GeminiContent
import com.genflowly.aicallerlib.models.gemini.GeminiGenerateContentRequest
import com.genflowly.aicallerlib.models.gemini.GeminiGenerateContentResponse
import com.genflowly.aicallerlib.models.gemini.GeminiGenerationConfig
import com.genflowly.aicallerlib.models.gemini.GeminiPart
import com.genflowly.aicallerlib.utils.Constants.GEMINI_BASE_API
import com.genflowly.aicallerlib.utils.Constants.GEMINI_GENERATE_CONTENT_ENDPOINT
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mu.KLogger
import java.io.IOException

class GoogleGeminiProvider(
    private val httpClient: HttpClient,
    private val logger: KLogger,
    private val json: Json
) : AIProvider<GeminiGenerateContentResponse> {
    override suspend fun generateResponse(apiKey: String, config: AIRequestConfig):
            GeminiGenerateContentResponse {
        return try {
            val generationConfig = GeminiGenerationConfig(
                temperature = config.temperature,
                topP = config.topP,
                topK = config.topK,
                maxOutputTokens = config.maxOutputTokens,
                stopSequences = config.stopSequences
            )

            val requestBody = GeminiGenerateContentRequest(

                contents = listOf(
                    GeminiContent(
                        parts = listOfNotNull(config.content?.let { GeminiPart(text = it) }),
                        role = config.role
                    )
                ),
                generationConfig = generationConfig.copy(
                    // Only include non-null values for cleaner requests
                    temperature = generationConfig.temperature,
                    topP = generationConfig.topP,
                    topK = generationConfig.topK,
                    maxOutputTokens = generationConfig.maxOutputTokens,
                    stopSequences = generationConfig.stopSequences
                ).takeIf {
                    it.temperature != null || it.topP != null || it.topK != null || it.maxOutputTokens != null || !it.stopSequences.isNullOrEmpty()
                },
                // Add safety settings if needed, for example:
                // safetySettings = listOf(
                //     GeminiSafetySetting(category = "HARM_CATEGORY_HARASSMENT", threshold = "BLOCK_NONE")
                // )
            )

            val endpoint = GEMINI_GENERATE_CONTENT_ENDPOINT.replace("{model}", config.model)
            val fullUrl = "$GEMINI_BASE_API$endpoint"

            logger.info { "Sending request to Gemini with model: ${config.model}, prompt: ${config.content}" }

            val response: HttpResponse = httpClient.post(fullUrl) {
                // Gemini API uses an API key in the URL for direct calls, or you can use other authentication methods.
                // For simplicity here, we're assuming a direct API key in the query parameter.
                // In a production environment, consider using more secure authentication methods like OAuth 2.0.
                url {
                    parameters.append("key", apiKey)
                }
                contentType(ContentType.Application.Json)
                setBody(json.encodeToString(requestBody))
            }

            if (response.status != HttpStatusCode.OK) {
                throw IOException("Failed to call Gemini API: ${response.status}, ${response.bodyAsText()}")
            }
            val responseBody: GeminiGenerateContentResponse =
                json.decodeFromString(response.bodyAsText())
            logger.info { "Received response from Gemini: ${responseBody.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text?.trim()}" }
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