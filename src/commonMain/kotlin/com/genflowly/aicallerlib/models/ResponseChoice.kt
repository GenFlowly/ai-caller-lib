package com.genflowly.aicallerlib.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single choice in the response.
 *
 * @param index The index of the choice in the list of completions.
 * @param message The message object returned as a completion.
 * @param finishReason The reason why the completion stopped, e.g., "stop", "length".
 */
@Serializable
data class ResponseChoice(
    val index: Int,
    val message: Message,
    @SerialName("finish_reason") val finishReason: String? = null,
    val logprobs: OpenAILogprobs? = null
)
