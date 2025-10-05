package com.genflowly.aicallerlib.models.openai
import com.genflowly.aicallerlib.models.GenerationConfigurable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a request to the OpenAI Chat API's `chat/create` endpoint
 * Source - https://platform.openai.com/docs/api-reference/chat/create
 *
 * @param model The identifier of the model to use (e.g., "gpt-4").
 * @param messages A list of message objects that make up the conversation.
 * @param temperature Sampling temperature. Higher values like 0.8 make the output more random,
 * while lower values like 0.2 make it more focused and deterministic.
 * @param topP An alternative to sampling with temperature, called nucleus sampling.
 * @param n How many chat completion choices to generate for each input message.
 * @param stream If set to true, partial message deltas will be sent, allowing the response to be
 * streamed.
 * @param stop Up to 4 sequences where the API will stop generating further tokens. The returned
 * text will not contain the stop sequence. It can be a single string or an array of strings.
 * @param maxTokens The maximum number of tokens to generate in the chat completion.
 * @param presencePenalty Number between -2.0 and 2.0. Positive values penalize new tokens based
 * on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.
 * @param frequencyPenalty Number between -2.0 and 2.0. Positive values penalize new tokens based
 * on their existing frequency in the text so far, reducing the model's likelihood to repeat the
 * same line verbatim.
 * @param logitBias Modify the likelihood of specified tokens appearing in the completion.
 * @param user A unique identifier representing your end-user, which can help OpenAI to monitor
 * and detect abuse.
 * @param logprobs Whether to return log probabilities of the output tokens or not. If true,
 * returns the log probabilities of each output token returned in the content of message.
 * @param topLogprobs An integer between 0 and 20 specifying the number of most likely tokens to
 * return at each token position, each with an associated log probability. logprobs must be set
 * to true if this parameter is used.
 * @param seed If specified, OpenAI will make a best effort to sample deterministically, such
 * that repeated requests with the same seed and parameters should return the same result.
 * @param serviceTier Specifies the latency tier to use for processing the request.
 */
@Serializable
data class OpenAIChatCreateRequest(
    val messages: List<OpenAIMessageRequest>,
    val stream: Boolean? = null,
    val stop: List<String>? = null,
    val n: Int? = null,
    @SerialName("max_tokens") val maxTokens: Int? = null,
    @SerialName("presence_penalty") val presencePenalty: Double? = null,
    @SerialName("frequency_penalty") val frequencyPenalty: Double? = null,
    @SerialName("logit_bias") val logitBias: Map<String, Double>? = null,
    val user: String? = null,
    val logprobs: Boolean? = null,
    @SerialName("top_logprobs") val topLogprobs: Int? = null,
    val seed: Int? = null,
    override val model: String,
    override val serviceTier: String? = null,
    override val temperature: Double? = null,
    override val topK: Double? = null,
    @SerialName("top_p") override val topP: Double? = null,
    // TODO Add tools array
): OpenAIChatCreate(), GenerationConfigurable {
    override val candidateCount = n
    override val stopSequences = stop
    override val maxOutputTokens = maxTokens
}
