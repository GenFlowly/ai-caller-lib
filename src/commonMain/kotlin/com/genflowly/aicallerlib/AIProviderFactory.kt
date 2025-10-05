package com.genflowly.aicallerlib

import com.genflowly.aicallerlib.clients.AIProvider
import com.genflowly.aicallerlib.models.AIResponse
import com.genflowly.aicallerlib.utils.Constants.GOOGLE_GEMINI
import com.genflowly.aicallerlib.utils.Constants.OPENAI
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

class AIProviderFactory: KoinComponent {
    inline fun <reified T : AIResponse> getProvider(vendor: String): AIProvider<T> {
        return when (vendor) {
            OPENAI -> get<AIProvider<T>>(named(OPENAI))
            GOOGLE_GEMINI -> get<AIProvider<T>>(named(GOOGLE_GEMINI))
            else -> throw IllegalArgumentException("Unknown AI vendor: $vendor")
        }
    }
}