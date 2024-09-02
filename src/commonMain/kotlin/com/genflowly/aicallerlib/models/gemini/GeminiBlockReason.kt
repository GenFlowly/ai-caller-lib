package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Specifies the reason why the prompt was blocked.
 * Source - https://ai.google.dev/api/generate-content#blockreason
 */
@Serializable
enum class GeminiBlockReason {
    BLOCK_REASON_UNSPECIFIED,
    SAFETY,
    OTHER,
    BLOCKLIST,
    PROHIBITED_CONTENT
}