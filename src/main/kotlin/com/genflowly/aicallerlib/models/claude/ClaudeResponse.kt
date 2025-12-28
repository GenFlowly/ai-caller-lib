package com.genflowly.aicallerlib.models.claude

import com.anthropic.models.messages.Message
import com.genflowly.aicallerlib.models.AIResponse

import com.anthropic.models.messages.RawMessageStreamEvent

class ClaudeResponse : AIResponse {
    private val response: Message?
    private val streamEvent: RawMessageStreamEvent?

    constructor(response: Message) {
        this.response = response
        this.streamEvent = null
    }

    constructor(event: RawMessageStreamEvent) {
        this.response = null
        this.streamEvent = event
    }

    override fun getText(): String {
        if (streamEvent != null) {
            val deltaEvent = streamEvent.contentBlockDelta()
            if (deltaEvent.isPresent) {
                 val delta = deltaEvent.get().delta()
                 if (delta.text().isPresent) {
                     return delta.text().get().text()
                 }
            }
            return ""
        }

        if (response == null || response.content().isEmpty()) {
            return ""
        }

        val textContent = response.content()
            .mapNotNull { contentBlock ->
                contentBlock.text().orElse(null)
            }
            .joinToString("")

        return textContent.trim()

    }

    override fun getRawResponse(): Any {
        return response ?: streamEvent!!
    }

    override fun toString(): String {
        return "ClaudeResponse{" +
                "response=" + response +
                ", streamEvent=" + streamEvent +
                '}'
    }
}