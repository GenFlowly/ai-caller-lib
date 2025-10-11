package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.AIRequest
import com.google.genai.types.GenerateContentParameters

class GeminiRequest(private val params: GenerateContentParameters) : AIRequest {

    override fun toString(): String {
        return "GeminiRequest{" +
                "config=" + params +
                '}'
    }

    fun getRequest(): GenerateContentParameters {
        return params
    }


}