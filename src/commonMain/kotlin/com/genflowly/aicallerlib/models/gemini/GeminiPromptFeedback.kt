package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * A set of the feedback metadata the prompt specified in GenerateContentRequest.content.
 * Source - https://ai.google.dev/api/generate-content#promptfeedback
 *
 * @param blockReason If set, the prompt was blocked and no candidates are returned.
 * Rephrase the prompt.
 * @param safetyRatings Ratings for safety of the prompt. There is at most one rating per category.
 */
@Serializable
data class GeminiPromptFeedback(
    val blockReason: GeminiBlockReason,
    val safetyRatings: List<GeminiSafetyRating>? = null
)
