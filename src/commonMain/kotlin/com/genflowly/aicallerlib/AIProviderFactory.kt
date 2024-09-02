package com.genflowly.aicallerlib

import com.genflowly.aicallerlib.clients.AIProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

class AIProviderFactory: KoinComponent {
    fun getProvider(vendor: String): AIProvider {
        return when (vendor) {
            "OpenAI" -> get<AIProvider>(named("OpenAI"))
            else -> throw IllegalArgumentException("Unknown AI vendor: $vendor")
        }
    }
}