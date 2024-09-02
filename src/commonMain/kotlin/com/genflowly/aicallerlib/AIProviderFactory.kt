package com.genflowly.aicallerlib.di

import com.genflowly.aicallerlib.provider.AIProvider
import com.genflowly.aicallerlib.provider.OpenAIProvider
import io.ktor.client.*

class AIProviderFactory(private val httpClient: HttpClient) {
    fun getProvider(vendor: String): AIProvider {
        return when (vendor) {
            "OpenAI" -> OpenAIProvider(httpClient)
            else -> throw IllegalArgumentException("Unknown AI vendor: $vendor")
        }
    }
}