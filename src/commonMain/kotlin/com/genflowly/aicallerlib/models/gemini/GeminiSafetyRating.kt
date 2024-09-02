package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.HarmCategory
import com.genflowly.aicallerlib.models.HarmProbability
import kotlinx.serialization.Serializable

/**
 * Safety rating for a piece of content.
 * Source - https://ai.google.dev/api/generate-content#safetyrating
 *
 * @param probability The probability of harm for this content.
 * @param blocked Was this content blocked because of this rating?
 * @param category The category for this rating.
 */
@Serializable
data class GeminiSafetyRating(
    val probability: HarmProbability,
    val blocked: Boolean,
    override val category: HarmCategory
): GeminiSafety()
