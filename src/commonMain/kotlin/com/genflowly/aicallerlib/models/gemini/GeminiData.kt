package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.Blob
import kotlinx.serialization.Serializable

/**
 * Union field data can be only one of the following.
 * Source - https://ai.google.dev/api/caching#part
 */
@Serializable
sealed class GeminiData {
    data class Text(val text: String) : GeminiData()
    data class InlineData(val inlineData: Blob) : GeminiData()
    data class FunctionCall(val functionCall: GeminiFunctionCall) : GeminiData()
    data class FunctionResponse(val functionResponse: GeminiFunctionResponse) : GeminiData()
    data class FileData(val fileData: com.genflowly.aicallerlib.models.FileData) : GeminiData()
    data class ExecutableCode(val executableCode: GeminiExecutableCode) : GeminiData()
    data class CodeExecutionResult(val codeExecutionResult: GeminiCodeExecutionResult) : GeminiData()
}
