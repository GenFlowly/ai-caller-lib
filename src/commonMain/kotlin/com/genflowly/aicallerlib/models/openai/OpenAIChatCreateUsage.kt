package com.genflowly.aicallerlib.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the usage statistics for the request.
 *
 * @param promptTokens The number of tokens used in the prompt.
 * @param completionTokens The number of tokens used in the completion.
 * @param totalTokens The total number of tokens used (prompt + completion).
 */
@Serializable
data class OpenAIChatCreateUsage(
    @SerialName("prompt_tokens") val promptTokens: Int,
    @SerialName("completion_tokens") val completionTokens: Int,
    @SerialName("total_tokens") val totalTokens: Int
)
