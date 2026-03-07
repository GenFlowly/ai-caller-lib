package com.genflowly.aicallerlib.models.openai

import com.genflowly.aicallerlib.models.AIUsageMetadata

data class OpenAIUsageMetadata(
    val inputTokens: Int?,
    val outputTokens: Int?,
    val totalTokens: Int?,
    val inputTokensDetails: OpenAIInputTokensDetails?,
    val outputTokensDetails: OpenAIOutputTokensDetails?,
    val promptCacheKey: String? = null
) : AIUsageMetadata {
    override fun getInputTokenCount(): Long? = inputTokens?.toLong()
    override fun getOutputTokenCount(): Long? = outputTokens?.toLong()
    override fun getTotalTokenCount(): Long? = totalTokens?.toLong()
    override fun getTokenCountReadFromCache(): Long? = inputTokensDetails?.cachedTokens
}

data class OpenAIInputTokensDetails(
    val cachedTokens: Long?
)

data class OpenAIOutputTokensDetails(
    val reasoningTokens: Long?
)

