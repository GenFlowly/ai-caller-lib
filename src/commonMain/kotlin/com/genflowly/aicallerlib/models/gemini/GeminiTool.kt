package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

@Serializable
data class GeminiTool(
    val functionDeclarations: Array<GeminiFunctionDeclaration>? = null,
    val codeExecution: GeminiCodeExecution? = null
)