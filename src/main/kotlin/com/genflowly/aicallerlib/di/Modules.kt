package com.genflowly.aicallerlib.di

import com.genflowly.aicallerlib.clients.OpenAIProxyClient
import com.anthropic.client.AnthropicClient
import com.genflowly.aicallerlib.clients.AIClient
import com.genflowly.aicallerlib.clients.ClaudeProxyClient
import com.genflowly.aicallerlib.clients.GeminiProxyClient
import com.genflowly.aicallerlib.models.AIVendor
import com.genflowly.aicallerlib.models.claude.ClaudeModelsListResponse
import com.genflowly.aicallerlib.models.claude.ClaudeResponse
import com.genflowly.aicallerlib.models.gemini.GeminiModelsListResponse
import com.genflowly.aicallerlib.models.gemini.GeminiResponse
import com.genflowly.aicallerlib.models.openai.OpenAIModelsListResponse
import com.genflowly.aicallerlib.models.openai.OpenAIResponse
import com.google.genai.Client
import com.openai.client.OpenAIClient
import mu.KLogger
import mu.KotlinLogging.logger
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun commonModule(): Module = module {
    //
    // Shared singletons
    //
    single { provideJson() }
    single<KLogger> { logger("Logger") }


    //
    // Tenant-scoped low-level client factories (parameterized by API key)
    //
    factory { (apiKey: String) -> provideAnthropicClient(apiKey) as AnthropicClient }
    factory { (apiKey: String) -> provideGeminiClient(apiKey) as Client }
    factory { (apiKey: String) -> provideOpenAIClient(apiKey) as OpenAIClient }

    //
    // Tenant-scoped AIProvider factories
    //
    factory<AIClient<GeminiResponse, GeminiModelsListResponse>>(named(AIVendor.GEMINI)) { (apiKey:
    String) ->
        val client: Client = get { parametersOf(apiKey) }
        val log: KLogger = get()
        GeminiProxyClient(client, log)
    }

    factory<AIClient<ClaudeResponse, ClaudeModelsListResponse>>(named(AIVendor.CLAUDE)) { (apiKey:
    String) ->
        val client: AnthropicClient = get { parametersOf(apiKey) }
        val log: KLogger = get()
        ClaudeProxyClient(client, log)
    }

    factory<AIClient<OpenAIResponse, OpenAIModelsListResponse>>(named(AIVendor.OPENAI)) { (apiKey:
    String) ->
        val client: OpenAIClient = get { parametersOf(apiKey) }
        val log: KLogger = get()
        OpenAIProxyClient(client, log)
    }
}