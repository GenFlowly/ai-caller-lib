package com.genflowly.aicallerlib.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a response from the OpenAI Chat API's `chat/create` endpoint.
 *
 * @param id The unique identifier of the completion.
 * @param `object` The type of object returned, typically "chat.completion".
 * @param created The creation timestamp.
 * @param model The model that was used to generate the response.
 * @param choices A list of choices where each choice represents a possible completion.
 * @param usage An object that tracks the token usage statistics for the request.
 */
@Serializable
data class OpenAIChatCreateResponse(
    val id: String,
    @SerialName("object") val objectType: String,
    val created: Long,
    val model: String,
    val choices: List<ResponseChoice>,
    val usage: OpenAIChatCreateUsage? = null
)
