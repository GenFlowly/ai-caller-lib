package com.genflowly.aicallerlib.provider

import com.genflowly.aicallerlib.models.AIRequestConfig

interface AIProvider {
    suspend fun generateResponse(apiKey: String, config: AIRequestConfig): String
}
