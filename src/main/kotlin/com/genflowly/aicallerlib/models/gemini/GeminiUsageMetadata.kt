package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.AIUsageMetadata

data class GeminiUsageMetadata(

    val promptTokens: Int?,
    val completionTokens: Int?,
    val totalTokens: Int?,

    val cachedContentTokenCount: Int?,
    val thoughtsTokenCount: Int?,
    val toolUsePromptTokenCount: Int?,

    val cacheTokensDetails: List<GeminiModalityTokenCount>?,
    val candidatesTokensDetails: List<GeminiModalityTokenCount>?,
    val promptTokensDetails: List<GeminiModalityTokenCount>?,
    val toolUsePromptTokensDetails: List<GeminiModalityTokenCount>?,

    val trafficType: String?
) : AIUsageMetadata


data class GeminiModalityTokenCount(
    val modality: String?,
    val tokenCount: Int?
)
