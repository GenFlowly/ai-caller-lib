package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.Usage
import kotlinx.serialization.Serializable

/**
 * Metadata on the generation request's token usage
 * Source - https://ai.google.dev/api/generate-content#usagemetadata
 *
 * @param promptTokenCount Number of tokens in the prompt.
 * @param cachedContentTokenCount Number of tokens in the cached part of the prompt (the cached content)
 * @param candidatesTokenCount Total number of tokens across all the generated response candidates.
 * @param totalTokenCount Total token count for the generation request (prompt + response candidates).
 */
@Serializable
data class GeminiUsageMetadata(
    val promptTokenCount: Int? = null,
    val cachedContentTokenCount: Int? = null,
    val candidatesTokenCount: Int? = null,
    val totalTokenCount: Int? = null
): Usage() {
    override val promptTokens = promptTokenCount
    override val completionTokens = candidatesTokenCount
    override val totalTokens = totalTokenCount
}
