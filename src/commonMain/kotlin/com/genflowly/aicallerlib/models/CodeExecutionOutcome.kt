package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

/**
 * Result of executing the ExecutableCode defined in Gemini.
 * Source - https://ai.google.dev/api/caching#codeexecutionresult
 */
@Serializable
enum class CodeExecutionOutcome {
    OUTCOME_UNSPECIFIED,
    OUTCOME_OK,
    OUTCOME_FAILED,
    OUTCOME_DEADLINE_EXCEEDED
}