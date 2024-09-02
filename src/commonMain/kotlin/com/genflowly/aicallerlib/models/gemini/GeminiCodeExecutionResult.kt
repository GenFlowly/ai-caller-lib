package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.CodeExecutionOutcome
import kotlinx.serialization.Serializable

/**
 * Result of executing the ExecutableCode.
 * Source - https://ai.google.dev/api/caching#codeexecutionresult
 *
 * @param outcome Outcome of the code execution.
 * @param output Contains stdout when code execution is successful, stderr or other description.
 */
@Serializable
data class GeminiCodeExecutionResult(
    val outcome: CodeExecutionOutcome,
    val output: String? = null
)
