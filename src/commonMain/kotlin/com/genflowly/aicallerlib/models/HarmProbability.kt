package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

/**
 * The probability that a piece of content is harmful defined in Gemini.
 * Source - https://ai.google.dev/api/generate-content#harmprobability
 */
@Serializable
enum class HarmProbability {
    HARM_PROBABILITY_UNSPECIFIED,
    NEGLIGIBLE,
    LOW,
    MEDIUM,
    HIGH
}