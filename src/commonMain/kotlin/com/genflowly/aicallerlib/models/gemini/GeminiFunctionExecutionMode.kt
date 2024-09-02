package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Defines the execution behavior for function calling by defining the execution mode.
 * Source - https://ai.google.dev/api/caching#mode
 */
@Serializable
enum class GeminiFunctionExecutionMode {
    MODE_UNSPECIFIED,
    AUTO,
    ANY,
    NONE
}