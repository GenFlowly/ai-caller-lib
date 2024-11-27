package com.genflowly.aicallerlib.di

import com.genflowly.aicallerlib.clients.AIProvider
import com.genflowly.aicallerlib.clients.OpenAIProvider
import com.genflowly.aicallerlib.models.openai.OpenAIChatCreateResponse
import com.genflowly.aicallerlib.utils.Constants.OPENAI
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
    single { provideJson() }
    single { provideHttpClient(get()) }
    single<KLogger> { logger("Logger") }
    single<AIProvider<OpenAIChatCreateResponse>>(named(OPENAI)) { OpenAIProvider(get(), get(), get()) }
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
        // Additional configurations for HttpClient can be added here
    }
}