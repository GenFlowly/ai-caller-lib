package com.genflowly.aicallerlib

import com.genflowly.aicallerlib.clients.AIClient
import com.genflowly.aicallerlib.models.AIModelsListResponse
import com.genflowly.aicallerlib.models.AIResponse
import com.genflowly.aicallerlib.models.AIVendor
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

class AIProviderFactory: KoinComponent {
    inline fun <reified T : AIResponse, reified U : AIModelsListResponse> getProvider(vendor: AIVendor): AIClient<T, U> {
        return when (vendor) {
            AIVendor.OPENAI -> get(named(AIVendor.OPENAI))
            AIVendor.GEMINI -> get(named(AIVendor.GEMINI))
            AIVendor.CLAUDE -> get(named(AIVendor.CLAUDE))
            else -> throw IllegalArgumentException("Unknown AI vendor: $vendor")
        }
    }
}