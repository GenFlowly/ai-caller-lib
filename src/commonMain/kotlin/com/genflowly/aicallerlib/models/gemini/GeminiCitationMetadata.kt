package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * A collection of source attributions for a piece of content.
 * Source - https://ai.google.dev/api/generate-content#citationmetadata
 *
 * @param citationSources Citations to sources for a specific response.
 */
@Serializable
data class GeminiCitationMetadata(
    val citationSources: List<GeminiCitationSource>? = null
)
