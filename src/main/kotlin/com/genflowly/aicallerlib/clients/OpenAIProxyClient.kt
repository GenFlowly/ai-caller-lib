package com.genflowly.aicallerlib.clients
import com.genflowly.aicallerlib.models.AIRequest
import com.genflowly.aicallerlib.models.openai.OpenAIModelsListResponse
import com.genflowly.aicallerlib.models.openai.OpenAIRequest
import com.genflowly.aicallerlib.models.openai.OpenAIResponse
import com.openai.client.OpenAIClient
import com.openai.models.models.ModelListPage
import com.openai.models.responses.Response
import com.openai.models.responses.ResponseCreateParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import mu.KLogger


class OpenAIProxyClient(
    private val client: OpenAIClient,
    private val logger: KLogger
) : AIClient<OpenAIResponse, OpenAIModelsListResponse> {

    override suspend fun generateResponse(
        request: AIRequest
    ): OpenAIResponse = withContext(Dispatchers.IO) {
        require(request is OpenAIRequest) {
            "AIRequest must be of type OpenAIRequest"
        }

        val params: ResponseCreateParams = request.getParams()

        val response: Response = client.responses().create(params)

        OpenAIResponse(response)
    }

    override suspend fun generateResponseStream(request: AIRequest): Flow<OpenAIResponse> {
        require(request is OpenAIRequest) {
            "AIRequest must be of type OpenAIRequest"
        }

        val params: ResponseCreateParams = request.getParams()

        return kotlinx.coroutines.flow.flow {
            val streamResponse = client.responses().createStreaming(params)
            streamResponse.use { stream ->
                for (chunk in stream.stream()) {
                    emit(OpenAIResponse(chunk))
                }
            }
        }
    }

    override suspend fun listAllModels(): OpenAIModelsListResponse =
        withContext(Dispatchers.IO) {
            val modelsPage: ModelListPage = client.models().list()
            logger.info { "Fetched ${modelsPage.data().size} OpenAI models" }
            OpenAIModelsListResponse(modelsPage)
        }


    override suspend fun listTextGenerationModels(): OpenAIModelsListResponse =
        withContext(Dispatchers.IO) {
            val allModels = listAllModels()

            val textModels = allModels.getModels().filter { isTextGenerationModel(it.id()) }

            logger.info { "Filtered ${textModels.size} text-generation models from ${allModels.getModels().size} total" }

            OpenAIModelsListResponse.fromFilteredModels(textModels)
        }


    private fun isTextGenerationModel(id: String): Boolean {
        val lower = id.lowercase()
        return lower.startsWith("gpt-") || lower.startsWith("o1-") || lower.contains(
            "turbo"
        )
    }
}