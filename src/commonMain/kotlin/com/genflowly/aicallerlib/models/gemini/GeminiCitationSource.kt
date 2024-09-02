package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * A citation to a source for a portion of a specific response.
 * Source - https://ai.google.dev/api/generate-content#citationsource
 *
 * @param startIndex Start of segment of the response that is attributed to this source.
 * @param endIndex  End of the attributed segment, exclusive.
 * @param uri URI that is attributed as a source for a portion of the text.
 * @param license License for the GitHub project that is attributed as a source for segment.
 */
@Serializable
data class GeminiCitationSource(
    val startIndex: Int? = null,
    val endIndex: Int? = null,
    val uri:String? = null,
    val license:String? = null
)
