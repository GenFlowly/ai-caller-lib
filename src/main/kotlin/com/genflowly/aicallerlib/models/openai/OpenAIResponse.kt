package com.genflowly.aicallerlib.models.openai

import com.genflowly.aicallerlib.models.AIResponse
import com.openai.models.responses.Response
import com.openai.models.responses.ResponseOutputMessage
import java.util.*


class OpenAIResponse(response: Response) : AIResponse<Response> {
    private val response: Response

    init {
        this.response = response
    }

    override fun getText(): String {
        if (response.output().isEmpty()) {
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

    override fun getRawResponse(): Response {
        return response
    }

    override fun toString(): String {
        return "OpenAIResponse{" +
                "response=" + response +
                '}'
    }
}