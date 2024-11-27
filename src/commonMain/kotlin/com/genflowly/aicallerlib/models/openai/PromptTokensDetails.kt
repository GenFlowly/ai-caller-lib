package com.genflowly.aicallerlib.models.openai

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Breakdown of tokens used in the prompt.
 * Source -  https://platform.openai.com/docs/api-reference/chat/object#chat/object-usage
 *
 * @param audioTokens Audio input tokens present in the prompt.
 * @param cachedTokens Cached tokens present in the prompt.
 */
@Serializable
data class PromptTokensDetails(
    @SerialName("audio_tokens") val audioTokens: Int,
    @SerialName("cached_tokens") val cachedTokens: Int
)
