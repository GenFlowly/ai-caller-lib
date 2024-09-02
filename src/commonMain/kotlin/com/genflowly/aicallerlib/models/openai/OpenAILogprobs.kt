package com.genflowly.aicallerlib.models.openai

import kotlinx.serialization.Serializable

/**
 * A list of message content tokens with log probability information.
 * Source - https://platform.openai.com/docs/api-reference/chat/object#chat/object-choices
 *
 * @param content A list of message content tokens with log probability information.
 */
@Serializable
data class OpenAILogprobs(
    val content: OpenAILogprobContent? = null
)
