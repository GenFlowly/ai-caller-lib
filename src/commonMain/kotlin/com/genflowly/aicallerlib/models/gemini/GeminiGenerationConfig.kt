package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.GenerationConfigurable
import kotlinx.serialization.Serializable

/**
 * Configuration options for model generation and outputs. Not all parameters are configurable for
 * every model.
 * Source - https://ai.google.dev/api/generate-content#generationconfig
 *
 * @param topK The maximum number of tokens to consider when sampling.
 * @param responseMimeType MIME type of the generated candidate text. Supported MIME types are:
 * text/plain: (default) Text output. application/json: JSON response in the response candidates.
 * @param responseSchema Output schema of the generated candidate text.
 * @param stopSequences The set of character sequences (up to 5) that will stop output generation.
 * @param candidateCount Number of generated responses to return.
 * @param temperature Controls the randomness of the output.
 * @param topP The maximum cumulative probability of tokens to consider when sampling.
 * @param maxOutputTokens The maximum number of tokens to include in a response candidate.
 */
@Serializable
data class GeminiGenerationConfig(
    override val topK: Double? = null,
    val responseMimeType: String? = null,
    val responseSchema: GeminiSchema? = null,
    override val stopSequences: List<String>? = null,
    override val candidateCount: Int? = null,
    override val temperature: Double? = null,
    override val topP: Double? = null,
    override val maxOutputTokens: Int? = null
): GenerationConfigurable
