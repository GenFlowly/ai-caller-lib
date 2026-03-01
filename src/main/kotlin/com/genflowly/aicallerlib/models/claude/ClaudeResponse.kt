package com.genflowly.aicallerlib.models.claude

import com.anthropic.models.messages.Message
import com.anthropic.models.messages.MessageDeltaUsage
import com.anthropic.models.messages.RawMessageStreamEvent
import com.anthropic.models.messages.Usage
import com.genflowly.aicallerlib.models.AIResponse
import com.genflowly.aicallerlib.models.AIUsageMetadata

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

    override fun getUsageMetadata(): AIUsageMetadata? {
        val msg = response
        if (msg != null) {
            return runCatching { msg.usage() }.getOrNull()?.toClaudeUsageMetadata()
        }
        val event = streamEvent
        if (event != null) {
            // message_start carries input-side Usage (with inputTokens, cache fields)
            event.messageStart().orElse(null)?.let { startEvent ->
                return runCatching { startEvent.message().usage() }.getOrNull()?.toClaudeUsageMetadata()
            }
            // message_delta carries MessageDeltaUsage (with final outputTokens)
            event.messageDelta().orElse(null)?.let { deltaEvent ->
                return runCatching { deltaEvent.usage() }.getOrNull()?.toClaudeUsageMetadata()
            }
        }
        return null

    }

    private fun Usage.toClaudeUsageMetadata(): ClaudeUsageMetadata {
        return ClaudeUsageMetadata(
            inputTokens = runCatching { inputTokens() }.getOrNull(),
            outputTokens = runCatching { outputTokens() }.getOrNull(),
            cacheCreationInputTokens = cacheCreationInputTokens().orElse(null),
            cacheReadInputTokens = cacheReadInputTokens().orElse(null),
            cacheCreation = cacheCreation().orElse(null)?.let { cc ->
                ClaudeCacheCreation(
                    ephemeral1hInputTokens = runCatching { cc.ephemeral1hInputTokens() }.getOrNull(),
                    ephemeral5mInputTokens = runCatching { cc.ephemeral5mInputTokens() }.getOrNull()
                )
            },
            serverToolUse = serverToolUse().orElse(null)?.let { stu ->
                ClaudeServerToolUsage(
                    webSearchRequests = runCatching { stu.webSearchRequests() }.getOrNull()
                )
            },
            serviceTier = serviceTier().orElse(null)?.toString()
        )
    }

    private fun MessageDeltaUsage.toClaudeUsageMetadata(): ClaudeUsageMetadata {
        return ClaudeUsageMetadata(
            inputTokens = inputTokens().orElse(null),
            outputTokens = runCatching { outputTokens() }.getOrNull(),
            cacheCreationInputTokens = cacheCreationInputTokens().orElse(null),
            cacheReadInputTokens = cacheReadInputTokens().orElse(null),
            cacheCreation = null,
            serverToolUse = serverToolUse().orElse(null)?.let { stu ->
                ClaudeServerToolUsage(
                    webSearchRequests = runCatching { stu.webSearchRequests() }.getOrNull()
                )
            },
            serviceTier = null
        )
    }

    override fun toString(): String {
        return "ClaudeResponse{" +
                "response=" + response +
                ", streamEvent=" + streamEvent +
                '}'
    }
}