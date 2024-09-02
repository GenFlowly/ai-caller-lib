package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * A datatype containing media that is part of a multi-part Content message.
 * Source - https://ai.google.dev/api/caching#part
 *
 * @param data Union field data.
 */
@Serializable
data class GeminiPart(
    val data: GeminiData
)
