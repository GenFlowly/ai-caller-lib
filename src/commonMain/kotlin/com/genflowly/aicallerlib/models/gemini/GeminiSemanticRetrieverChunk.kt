package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Identifier for a Chunk retrieved via Semantic Retriever specified in the GenerateAnswerRequest
 * using SemanticRetrieverConfig.
 * Source - https://ai.google.dev/api/generate-content#semanticretrieverchunk
 *
 * @param source Name of the source matching the request's SemanticRetrieverConfig.source.
 * @param chunk Output only. Name of the Chunk containing the attributed text.
 */
@Serializable
data class GeminiSemanticRetrieverChunk(
    val source: String? = null,
    val chunk: String? = null
)
