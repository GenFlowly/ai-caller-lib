package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

@Serializable
data class GeminiRequest(
    val contents: List<GeminiContent>,
    val tools: List<GeminiTool>? = null,
    val toolConfig: GeminiToolConfig? = null,
    val safetySettings: GeminiSafetySetting? = null,
    val systemInstruction: GeminiContent? = null,
    val cachedContent: String? = null,
    val generationConfig: GeminiGenerationConfig? = null
)
