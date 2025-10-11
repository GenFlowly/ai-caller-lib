package com.genflowly.aicallerlib.di

import OpenAIProvider
import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.genflowly.aicallerlib.clients.AIProvider
import com.genflowly.aicallerlib.clients.ClaudeProvider
import com.genflowly.aicallerlib.clients.GeminiProvider
import com.genflowly.aicallerlib.models.AIVendor
import com.genflowly.aicallerlib.models.claude.ClaudeResponse
import com.genflowly.aicallerlib.models.gemini.GeminiResponse
import com.genflowly.aicallerlib.models.openai.OpenAIResponse
import com.google.genai.Client
import com.openai.client.OpenAIClient
import com.openai.client.okhttp.OpenAIOkHttpClient
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import mu.KLogger
import mu.KotlinLogging.logger
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun commonModule(): Module = module {
    // Common Modules
    single { provideJson() }
    single<KLogger> { logger("Logger") }

    // HTTP Client Modules
    single { provideHttpClient(get()) }
    single { provideAnthropicClient(apiKey = getProperty("claude.api.key")) }
    single { provideGeminiClient(apiKey = getProperty("gemini.api.key")) }
    single { provideOpenAIClient(apiKey = getProperty("openai.api.key")) }

    // AI Vendor Modules
    single<AIProvider<GeminiResponse>>(named(AIVendor.GEMINI)) {
        GeminiProvider(get(), get())
    }
    single<AIProvider<ClaudeResponse>>(named(AIVendor.CLAUDE)) {
        ClaudeProvider(get(), get())
    }
    single<AIProvider<OpenAIResponse>>(named(AIVendor.OPENAI)) {
        OpenAIProvider(get(), get())
    }
}

fun provideJson(): Json {
    return Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}

fun provideHttpClient(json: Json): HttpClient {
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }
}

fun provideAnthropicClient(apiKey: String): AnthropicClient {
    return AnthropicOkHttpClient.builder()
        .apiKey(apiKey)
        .build()
}

fun provideGeminiClient(apiKey: String): Client {
    return Client.builder()
        .apiKey(apiKey)
        .build()
}

fun provideOpenAIClient(apiKey: String): OpenAIClient {
    return OpenAIOkHttpClient.builder()
        .apiKey(apiKey)
        .build()
}