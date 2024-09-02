package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

@Serializable
data class GeminiContent(
    val parts: Array<GeminiPart>,
    val role: Role
)
