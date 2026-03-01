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
) : AIUsageMetadata


data class ClaudeCacheCreation(
    val ephemeral1hInputTokens: Long?,
    val ephemeral5mInputTokens: Long?
)

data class ClaudeServerToolUsage(
    val webSearchRequests: Long?
)
