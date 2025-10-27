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


        ClaudeResponse(response)
    }

    override suspend fun listAllModels(): ClaudeModelsListResponse =
        withContext(Dispatchers.IO) {
            val modelsResponse: ModelListPage = client.models().list()
            logger.info { "Fetched ${modelsResponse.data().size} Claude models" }
            ClaudeModelsListResponse(modelsResponse)
        }

    override suspend fun listTextGenerationModels(): ClaudeModelsListResponse {
        TODO("Not yet implemented")
    }
}