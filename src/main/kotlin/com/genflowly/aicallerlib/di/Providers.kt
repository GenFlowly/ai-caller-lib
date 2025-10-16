package com.genflowly.aicallerlib.di

import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.google.genai.Client
import com.openai.client.OpenAIClient
import com.openai.client.okhttp.OpenAIOkHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun provideJson(): Json =
    Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

fun provideHttpClient(json: Json): HttpClient =
    HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }

fun provideAnthropicClient(apiKey: String): AnthropicClient =
    AnthropicOkHttpClient.builder()
        .apiKey(apiKey)
        .build()

fun provideGeminiClient(apiKey: String): Client =
    Client.builder()
        .apiKey(apiKey)
        .build()

fun provideOpenAIClient(apiKey: String): OpenAIClient =
    OpenAIOkHttpClient.builder()
        .apiKey(apiKey)
        .build()