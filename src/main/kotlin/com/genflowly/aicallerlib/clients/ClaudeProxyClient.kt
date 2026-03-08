package com.genflowly.aicallerlib.clients

import com.anthropic.client.AnthropicClient
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.models.ModelListPage
import com.genflowly.aicallerlib.models.AIRequest
import com.genflowly.aicallerlib.models.claude.ClaudeModelsListResponse
import com.genflowly.aicallerlib.models.claude.ClaudeRequest
import com.genflowly.aicallerlib.models.claude.ClaudeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import mu.KLogger

class ClaudeProxyClient(
    private val client: AnthropicClient,
    private val logger: KLogger
) : AIClient<ClaudeResponse, ClaudeModelsListResponse> {
    override suspend fun generateResponse(
        request: AIRequest
    ): ClaudeResponse = withContext(Dispatchers.IO) {
        require(request is ClaudeRequest) {
            "AIRequest must be of type AnthropicRequest"
        }

        val params: MessageCreateParams = request.getParams()

        val response: Message = client.messages().create(params)


        val aiResponse = ClaudeResponse(response)
        aiResponse.getUsageMetadata()?.let { usage ->
            logger.info { "Claude Raw Response: ${aiResponse.getRawResponse()}" }
            logger.info { "Claude Usage Metadata: $usage" }
        }
        aiResponse
    }

    override suspend fun generateResponseStream(request: AIRequest): Flow<ClaudeResponse> {
        require(request is ClaudeRequest) {
            "AIRequest must be of type ClaudeRequest"
        }

        val params: MessageCreateParams = request.getParams()

        return kotlinx.coroutines.flow.flow {
            val streamResponse = client.messages().createStreaming(params)
            streamResponse.use { stream ->
                for (chunk in stream.stream()) {
                    val aiResponse = ClaudeResponse(chunk)
                    aiResponse.getUsageMetadata()?.let { usage ->
                        logger.info { "Claude Raw Response: ${aiResponse.getRawResponse()}" }
                        logger.info { "Claude Usage Metadata: $usage" }
                    }
                    emit(aiResponse)
                }
            }
        }
    }

    override suspend fun listAllModels(): ClaudeModelsListResponse =
        withContext(Dispatchers.IO) {
            val modelsResponse: ModelListPage = client.models().list()
            logger.info { "Fetched ${modelsResponse.data().size} Claude models" }
            ClaudeModelsListResponse(modelsResponse)
        }

    override suspend fun listTextGenerationModels(): ClaudeModelsListResponse {
        return listAllModels()
    }
}