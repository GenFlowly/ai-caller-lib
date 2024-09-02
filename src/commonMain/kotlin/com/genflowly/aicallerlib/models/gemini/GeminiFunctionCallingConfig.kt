package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

@Serializable
data class GeminiFunctionCallingConfig(
    val mode: GeminiFunctionExecutionMode? = null,
    val allowedFunctionNames: List<String>? = null
)
