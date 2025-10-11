package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.AIRequest
import com.google.genai.types.GenerateContentParameters

class GeminiRequest(private val config: GenerateContentParameters) : AIRequest {

    override fun toString(): String {
        return "GeminiRequest{" +
                "config=" + config +
                '}'
    }

    fun getRequest(): GenerateContentParameters {
        return config
    }


}