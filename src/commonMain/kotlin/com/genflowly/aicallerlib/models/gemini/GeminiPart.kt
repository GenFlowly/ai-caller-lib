package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

@Serializable
data class GeminiPart(
    val data: GeminiData
)
