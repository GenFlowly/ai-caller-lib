package com.genflowly.aicallerlib.models.openai

import com.genflowly.aicallerlib.models.AIResponse
import com.openai.models.responses.Response
import com.openai.models.responses.ResponseOutputMessage
import java.util.*


import com.openai.models.responses.ResponseStreamEvent

class OpenAIResponse : AIResponse {
    private val response: Response?
    private val streamEvent: ResponseStreamEvent?

    constructor(response: Response) {
        this.response = response
        this.streamEvent = null
    }

    constructor(event: ResponseStreamEvent) {
        this.response = null
        this.streamEvent = event
    }

    override fun getText(): String {
        if (streamEvent != null) {
            val delta = streamEvent.outputTextDelta()
            return if (delta.isPresent) delta.get().delta() else ""
        }

        if (response == null || response.output().isEmpty()) {
            return ""
        }

        val sb = StringBuilder()

        for (item in response.output()) {
            val messageOptional: Optional<ResponseOutputMessage> = item.message()
            if (messageOptional.isPresent) {
                val message = messageOptional.get()
                val contentList = message.content() ?: continue

                for (content in contentList) {
                    val outputText: String = content.outputText().get()
                        .text()
                    sb.append(outputText)
                }
            }
        }

        return sb.toString().trim()
    }

    override fun getRawResponse(): Any {
        return response ?: streamEvent!!
    }

    override fun toString(): String {
        return "OpenAIResponse{" +
                "response=" + response +
                ", streamEvent=" + streamEvent +
                '}'
    }
}