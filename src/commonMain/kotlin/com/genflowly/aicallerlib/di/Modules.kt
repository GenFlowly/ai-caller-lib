package com.genflowly.aicallerlib.di

import com.genflowly.aicallerlib.clients.ApiClient
import com.genflowly.aicallerlib.services.OpenAIService
import com.genflowly.aicallerlib.services.OpenAIServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun commonModule(
    openAIKey: String,
    model: String,
    maxTokens: Int,
    temperature: Double,
    topP: Double
): Module = module {
    single(named("openAIKey")) { openAIKey }
    single(named("model")) { model }
    single(named("maxTokens")) { maxTokens }
    single(named("temperature")) { temperature }
    single(named("topP")) { topP }
    single { provideHttpClient() }
    single { ApiClient() }
    single<OpenAIService> { OpenAIServiceImpl(get()) }
}

fun provideHttpClient(): HttpClient {
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        // Additional configurations for HttpClient can be added here
    }
}