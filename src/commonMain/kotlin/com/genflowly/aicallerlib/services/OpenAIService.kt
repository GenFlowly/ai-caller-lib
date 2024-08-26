package com.genflowly.aicallerlib.services

import com.genflowly.aicallerlib.clients.ApiClient

interface OpenAIService {
    suspend fun generateResponse(prompt: String): String
}

class OpenAIServiceImpl(private val apiClient: ApiClient) : OpenAIService {
    override suspend fun generateResponse(prompt: String): String {
        return apiClient.callOpenAI(prompt)
    }
}