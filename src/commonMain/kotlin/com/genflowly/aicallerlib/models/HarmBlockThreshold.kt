package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

/**
 * Block at and beyond a specified harm probability defined in Gemini.
 * Source - https://ai.google.dev/api/generate-content#harmblockthreshold
 */
@Serializable
enum class HarmBlockThreshold {
    HARM_BLOCK_THRESHOLD_UNSPECIFIED,
    BLOCK_LOW_AND_ABOVE,
    BLOCK_MEDIUM_AND_ABOVE,
    BLOCK_ONLY_HIGH,
    BLOCK_NONE
}