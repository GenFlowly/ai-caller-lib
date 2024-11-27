package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequestConfig
import com.genflowly.aicallerlib.models.AIResponse

interface AIProvider<T : AIResponse> {
    suspend fun generateResponse(apiKey: String, config: AIRequestConfig): T
}
