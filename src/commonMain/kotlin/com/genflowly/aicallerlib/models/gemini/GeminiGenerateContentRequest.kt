package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * The request body for Gemini
 * Source - https://ai.google.dev/api/generate-content#request-body
 *
 * @param contents The content of the current conversation with the model.
 * @param tools A list of Tools the Model may use to generate the next response.
 * @param toolConfig Tool configuration for any Tool specified in the request.
 * Refer to the Function calling guide for a usage example.
 * @param safetySettings A list of unique SafetySetting instances for blocking unsafe content.
 * @param systemInstruction Developer set system instruction(s).
 * @param cachedContent The name of the content cached to use as context to serve the prediction.
 * @param generationConfig Configuration options for model generation and outputs.
 */
@Serializable
data class GeminiGenerateContentRequest(
    val contents: List<GeminiContent>,
    val tools: List<GeminiTool>? = null,
    val toolConfig: GeminiToolConfig? = null,
    val safetySettings: GeminiSafetySetting? = null,
    val systemInstruction: GeminiContent? = null,
    val cachedContent: String? = null,
    val generationConfig: GeminiGenerationConfig? = null,
)
