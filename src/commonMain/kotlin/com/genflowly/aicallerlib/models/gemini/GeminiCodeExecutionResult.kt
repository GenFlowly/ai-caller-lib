package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

@Serializable
data class GeminiCodeExecutionResult(
    val outcome: CodeExecutionOutcome,
    val output: String? = null
)
