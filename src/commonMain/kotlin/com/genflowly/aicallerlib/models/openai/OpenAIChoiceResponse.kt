package com.genflowly.aicallerlib.models.openai
import com.genflowly.aicallerlib.models.CandidateResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single choice in the response.
 * Source -  https://platform.openai.com/docs/api-reference/chat/object#chat/object-choices
 *
 * @param index The index of the choice in the list of completions.
 * @param message The message object returned as a completion.
 * @param finishReason The reason why the completion stopped, e.g., "stop", "length".
 */
@Serializable
data class OpenAIChoiceResponse(
    val index: Int,
    val message: OpenAIMessageResponse,
    @SerialName("finish_reason") override val finishReason: String? = null,
    val logprobs: OpenAILogprobs? = null
): CandidateResponse<OpenAIMessageResponse, String?>() {
    override val content = message
}
