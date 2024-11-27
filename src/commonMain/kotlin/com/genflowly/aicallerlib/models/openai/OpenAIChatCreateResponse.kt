package com.genflowly.aicallerlib.models.openai
import com.genflowly.aicallerlib.models.AIResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a response from the OpenAI Chat API's `chat/create` endpoint.
 * Source - https://ai.google.dev/api/caching#mode
 *
 * @param id The unique identifier of the completion.
 * @param `object` The type of object returned, typically "chat.completion".
 * @param created The creation timestamp.
 * @param choices A list of choices where each choice represents a possible completion.
 * @param usage An object that tracks the token usage statistics for the request.
 * @param systemFingerprint This fingerprint represents the backend configuration that the model
 * runs with.
 * @param model The model that was used to generate the response.
 * @param serviceTier Specifies the latency tier to use for processing the request.
 *
 */
@Serializable
data class OpenAIChatCreateResponse(
    val id: String,
    @SerialName("object") val objectType: String,
    val created: Long,
    val choices: List<OpenAIChoiceResponse>,
    val usage: OpenAIChatCreateUsage? = null,
    @SerialName("system_fingerprint") val systemFingerprint: String? = null,
    override val model: String,
    override val serviceTier: String? = null
): OpenAIChatCreate(), AIResponse
