package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequest
import com.genflowly.aicallerlib.models.gemini.GeminiModelsListResponse
import com.genflowly.aicallerlib.models.gemini.GeminiRequest
import com.genflowly.aicallerlib.models.gemini.GeminiResponse
import com.google.genai.Client
import com.google.genai.Pager
import com.google.genai.types.ListModelsConfig
import com.google.genai.types.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import mu.KLogger

class GeminiProxyClient(
    private val client: Client,
    private val logger: KLogger
) : AIClient<GeminiResponse, GeminiModelsListResponse> {

    override suspend fun generateResponse(
        request: AIRequest
    ): GeminiResponse = withContext(Dispatchers.IO) {
        require(request is GeminiRequest) {
            "AIRequest must be of type GeminiRequest"
        }

        val response = client.models.generateContent(
            request.getRequest().model().get(),
            request.getRequest().contents().get(),
            request.getRequest().config().orElse(null)
        )


        val aiResponse = GeminiResponse(response)
        aiResponse.getUsageMetadata()?.let { usage ->
            logger.info { "Gemini Raw Response: ${aiResponse.getRawResponse()}" }
            logger.info { "Gemini Usage Metadata: $usage" }
        }
        aiResponse
    }

    override suspend fun generateResponseStream(
        request: AIRequest
    ): Flow<GeminiResponse> {
        require(request is GeminiRequest) {
            "AIRequest must be of type GeminiRequest"
        }

        return kotlinx.coroutines.flow.flow {
            val stream = client.models.generateContentStream(
                request.getRequest().model().get(),
                request.getRequest().contents().get(),
                request.getRequest().config().orElse(null)
            )
            for (response in stream) {
                val aiResponse = GeminiResponse(response)
                aiResponse.getUsageMetadata()?.let { usage ->
                    logger.info { "Gemini Raw Response: ${aiResponse.getRawResponse()}" }
                    logger.info { "Gemini Usage Metadata: $usage" }
                }
                emit(aiResponse)
            }
        }
    }

    override suspend fun listAllModels(): GeminiModelsListResponse =
        withContext(Dispatchers.IO) {
            val config: ListModelsConfig = ListModelsConfig.builder().build()
            val models: Pager<Model> = client.models.list(config)
            logger.info { "Fetched $models Gemini models" }
            GeminiModelsListResponse(models)
        }

    override suspend fun listTextGenerationModels(): GeminiModelsListResponse =
        withContext(Dispatchers.IO) {
            val allModelsResponse = listAllModels()
            val allModels = allModelsResponse.getModels()

            val textModels = allModels.filter { model ->
                val id = model.name().orElse("").lowercase()
                isTextGenerationModel(id)
            }

            logger.info { "Filtered ${textModels.size} Gemini text-generation models" }

            GeminiModelsListResponse(filteredModels = textModels)
        }

    private fun isTextGenerationModel(id: String): Boolean {
        // Include only Gemini/Gemma/LearnLM style models meant for text
        val include = listOf("gemini", "gemma", "learnlm")

        // Exclude embeddings, image, video, audio, robotics, etc.
        val exclude = listOf(
            "embedding",
            "imagen",
            "veo",
            "tts",
            "audio",
            "robotics",
            "computer-use",
            "native-audio"
        )

        return include.any { id.contains(it) } && exclude.none { id.contains(it) }
    }
}