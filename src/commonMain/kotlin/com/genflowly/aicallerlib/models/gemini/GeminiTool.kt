package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Tool details that the model may use to generate response.
 * Source - https://ai.google.dev/api/caching#tool
 *
 * @param functionDeclarations A list of FunctionDeclarations available to the model that can be
 * used for function calling.
 * @param codeExecution Enables the model to execute code as part of generation.
 *
 */
@Serializable
data class GeminiTool(
    val functionDeclarations: List<GeminiFunctionDeclaration>? = null,
    val codeExecution: GeminiCodeExecution? = null
)