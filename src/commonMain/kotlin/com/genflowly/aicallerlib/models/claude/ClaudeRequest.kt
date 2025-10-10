package com.genflowly.aicallerlib.models.claude

import com.anthropic.models.messages.MessageCreateParams
import com.genflowly.aicallerlib.models.AIRequest

class ClaudeRequest(private val params: MessageCreateParams) : AIRequest {
    override fun toString(): String {
        return "AnthropicRequest{" +
                "params=" + params +
                '}'
    }

    fun getParams(): MessageCreateParams {
        return params
    }
}
