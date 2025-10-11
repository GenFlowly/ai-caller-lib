package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.AIResponse
import com.google.genai.types.GenerateContentResponse

class GeminiResponse(response: GenerateContentResponse) : AIResponse {
    private val response: GenerateContentResponse

    init {
        this.response = response
    }

    override fun getText(): String? {
        return response.text()?.trim();
    }

    override fun getRawResponse(): GenerateContentResponse {
        return response
    }

}