package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequestConfig
import com.genflowly.aicallerlib.models.openai.OpenAIChatCreateResponse

interface AIProvider {
    suspend fun generateResponseFromOpenAI(apiKey: String, config: AIRequestConfig): OpenAIChatCreateResponse
}
