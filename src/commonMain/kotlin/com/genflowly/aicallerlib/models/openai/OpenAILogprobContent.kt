package com.genflowly.aicallerlib.models.openai

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A list of message content tokens with log probability information.
 * Source - https://platform.openai.com/docs/api-reference/chat/object#chat/object-choices
 *
 * @param token The token.
 * @param logprob The log probability of this token, if it is within the top 20 most likely tokens.
 * @param bytes A list of integers representing the UTF-8 bytes representation of the token.
 * @param topLogprobs List of the most likely tokens and their log probability, at this token position.
 */
@Serializable
data class OpenAILogprobContent(
    val token: String? = null ,
    val logprob: Int? = null,
    val bytes: List<Byte>? = null,
    @SerialName("top_logprobs") val topLogprobs: List<OpenAILogprobContent>? = null
)
