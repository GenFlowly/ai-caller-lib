package com.genflowly.aicallerlib

import com.genflowly.aicallerlib.clients.AIProvider
import com.genflowly.aicallerlib.models.AIResponse
import com.genflowly.aicallerlib.models.AIVendor
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

class AIProviderFactory: KoinComponent {
    inline fun <reified T : AIResponse> getProvider(vendor: AIVendor): AIProvider<T> {
        return when (vendor) {
            AIVendor.OPENAI -> get<AIProvider<T>>(named(AIVendor.OPENAI))
            AIVendor.GEMINI -> get<AIProvider<T>>(named(AIVendor.GEMINI))
            AIVendor.CLAUDE -> get<AIProvider<T>>(named(AIVendor.CLAUDE))
            else -> throw IllegalArgumentException("Unknown AI vendor: $vendor")
        }
    }
}