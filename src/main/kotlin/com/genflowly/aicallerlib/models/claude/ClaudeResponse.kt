package com.genflowly.aicallerlib.models.claude

import com.anthropic.models.messages.Message
import com.genflowly.aicallerlib.models.AIResponse

class ClaudeResponse(response: Message) : AIResponse {
    private val response: Message

    init {
        this.response = response
    }

    override fun getText(): String {
        if (response.content().isEmpty()) {
            return ""
        }

        val textContent = response.content()
            .mapNotNull { contentBlock ->
                contentBlock.text().orElse(null)?.text()
            }
            .joinToString("")

        return textContent.trim()

    }

    override fun getRawResponse(): Message {
        return response
    }

    override fun toString(): String {
        return "ClaudeResponse{" +
                "response=" + response +
                '}'
    }
}