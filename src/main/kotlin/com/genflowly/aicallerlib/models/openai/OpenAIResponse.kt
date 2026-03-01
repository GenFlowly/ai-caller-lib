package com.genflowly.aicallerlib.models.openai

import com.genflowly.aicallerlib.models.AIResponse
import com.genflowly.aicallerlib.models.AIUsageMetadata
import com.openai.models.responses.Response
import com.openai.models.responses.ResponseOutputMessage
import com.openai.models.responses.ResponseStreamEvent
import com.openai.models.responses.ResponseUsage
import java.util.*

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

    override fun getUsageMetadata(): AIUsageMetadata? {
        val usageSource: ResponseUsage? = when {
            response != null -> response.usage().orElse(null)
            streamEvent != null -> streamEvent.completed().orElse(null)
                ?.response()?.usage()?.orElse(null)
            else -> null
        }
        return usageSource?.toOpenAIUsageMetadata()
    }

    private fun ResponseUsage.toOpenAIUsageMetadata(): OpenAIUsageMetadata {
        val cachedTokens: Long? = runCatching { inputTokensDetails().cachedTokens() }.getOrNull()
        val reasoningTokens: Long? = runCatching { outputTokensDetails().reasoningTokens() }.getOrNull()

        return OpenAIUsageMetadata(
            inputTokens = runCatching { inputTokens().toInt() }.getOrNull(),
            outputTokens = runCatching { outputTokens().toInt() }.getOrNull(),
            totalTokens = runCatching { totalTokens().toInt() }.getOrNull(),
            inputTokensDetails = OpenAIInputTokensDetails(cachedTokens = cachedTokens),
            outputTokensDetails = OpenAIOutputTokensDetails(reasoningTokens = reasoningTokens)
        )
    }

    override fun toString(): String {
        return "OpenAIResponse{" +
                "response=" + response +
                ", streamEvent=" + streamEvent +
                '}'
    }
}