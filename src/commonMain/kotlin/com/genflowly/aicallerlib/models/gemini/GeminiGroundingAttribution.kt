package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Attribution for a source that contributed to an answer.
 * Source - https://ai.google.dev/api/generate-content#groundingattribution
 *
 * @param sourceId Identifier for the source contributing to this attribution.
 * @param content Grounding source content that makes up this attribution.
 */
@Serializable
data class GeminiGroundingAttribution(
    val sourceId: GeminiAttributionSourceId? = null,
    val content: GeminiContent? = null
)
