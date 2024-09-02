package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Defines the reason why the model stopped generating tokens.
 */
@Serializable
enum class GeminiFinishReason {
    FINISH_REASON_UNSPECIFIED,
    STOP,
    MAX_TOKENS,
    SAFETY,
    RECITATION,
    LANGUAGE,
    OTHER,
    BLOCKLIST,
    PROHIBITED_CONTENT,
    SPII,
    MALFORMED_FUNCTION_CALL
}