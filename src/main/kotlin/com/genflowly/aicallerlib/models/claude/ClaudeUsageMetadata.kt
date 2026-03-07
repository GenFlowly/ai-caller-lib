package com.genflowly.aicallerlib.models.claude

import com.genflowly.aicallerlib.models.AIUsageMetadata

data class ClaudeUsageMetadata(
    val inputTokens: Long?,
    val outputTokens: Long?,
    val cacheCreationInputTokens: Long?,
    val cacheReadInputTokens: Long?,
    val cacheCreation: ClaudeCacheCreation?,
    val serverToolUse: ClaudeServerToolUsage?,
    val serviceTier: String?
) : AIUsageMetadata {
    override fun getInputTokenCount(): Long? = inputTokens
    override fun getOutputTokenCount(): Long? = outputTokens
    override fun getTotalTokenCount(): Long? = (inputTokens ?: 0L) + (outputTokens ?: 0L)
    override fun getTokenCountReadFromCache(): Long? = cacheReadInputTokens
}


data class ClaudeCacheCreation(
    val ephemeral1hInputTokens: Long?,
    val ephemeral5mInputTokens: Long?
)

data class ClaudeServerToolUsage(
    val webSearchRequests: Long?
)
