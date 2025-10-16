package com.genflowly.aicallerlib.clients

import com.anthropic.client.AnthropicClient
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageCreateParams
import com.genflowly.aicallerlib.models.AIRequest
import com.genflowly.aicallerlib.models.claude.ClaudeRequest
import com.genflowly.aicallerlib.models.claude.ClaudeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KLogger

class ClaudeProxyClient(
    private val client: AnthropicClient,
    private val logger: KLogger
) : AIClient<ClaudeResponse> {
    override suspend fun generateResponse(
        request: AIRequest
    ): ClaudeResponse = withContext(Dispatchers.IO) {
        require(request is ClaudeRequest) {
            "AIRequest must be of type AnthropicRequest"
        }

        val params : MessageCreateParams = request.getParams()

        val response: Message = client.messages().create(params)


        ClaudeResponse(response)
    }
}