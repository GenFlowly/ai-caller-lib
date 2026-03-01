package com.genflowly.aicallerlib.models.openai

import com.genflowly.aicallerlib.models.AIUsageMetadata

data class OpenAIUsageMetadata(
    val inputTokens: Int?,
    val outputTokens: Int?,
    val totalTokens: Int?,
    val inputTokensDetails: OpenAIInputTokensDetails?,
    val outputTokensDetails: OpenAIOutputTokensDetails?
) : AIUsageMetadata

data class OpenAIInputTokensDetails(
    val cachedTokens: Long?
)

data class OpenAIOutputTokensDetails(
    val reasoningTokens: Long?
)

