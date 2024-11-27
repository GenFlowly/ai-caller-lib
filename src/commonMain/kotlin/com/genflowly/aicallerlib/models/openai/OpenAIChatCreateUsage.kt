package com.genflowly.aicallerlib.models.openai

import com.genflowly.aicallerlib.models.Usage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the usage statistics for the request.
 * Source - https://platform.openai.com/docs/api-reference/chat/object#chat/object-usage
 *
 * @param promptTokens The number of tokens used in the prompt.
 * @param completionTokens The number of tokens used in the completion.
 * @param totalTokens The total number of tokens used (prompt + completion).
 */
@Serializable
data class OpenAIChatCreateUsage(
    @SerialName("prompt_tokens") override val promptTokens: Int,
    @SerialName("completion_tokens") override val completionTokens: Int,
    @SerialName("total_tokens") override val totalTokens: Int,
    @SerialName("completion_tokens_details") val completionTokensDetails: Int,
    @SerialName("prompt_tokens_details") val promptTokensDetails: Int

): Usage()
