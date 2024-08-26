package com.genflowly.aicallerlib.di

import com.genflowly.aicallerlib.clients.ApiClient
import com.genflowly.aicallerlib.services.OpenAIService
import com.genflowly.aicallerlib.services.OpenAIServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(
    openAIKey: String,
    model: String,
    maxTokens: Int,
    temperature: Double,
    topP: Double
): Module = module {
    single { provideHttpClient() }
    single { ApiClient(get(), openAIKey, model, maxTokens, temperature, topP) }
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