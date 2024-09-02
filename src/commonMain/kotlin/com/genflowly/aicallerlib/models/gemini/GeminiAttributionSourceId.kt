package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Identifier for the source contributing to this attribution.
 * Source - https://ai.google.dev/api/generate-content#attributionsourceid
 *
 * @param groundingPassage Identifier for an inline passage.
 * @param semanticRetrieverChunk Identifier for a Chunk fetched via Semantic Retriever.
 */
@Serializable
data class GeminiAttributionSourceId(
    val groundingPassage: GeminiGroundingPassageId? = null,
    val semanticRetrieverChunk: GeminiSemanticRetrieverChunk? = null
)
