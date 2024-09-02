package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequestConfig

interface AIProvider {
    suspend fun generateResponse(apiKey: String, config: AIRequestConfig): String
}
