package com.genflowly.aicallerlib.utils

import com.genflowly.aicallerlib.AIProviderFactory
import com.genflowly.aicallerlib.clients.AIProvider
import com.genflowly.aicallerlib.di.commonModule
import com.genflowly.aicallerlib.models.openai.OpenAIChatCreateResponse
import io.mockk.mockk
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AIProviderFactoryTest : KoinTest {

    private val factory by inject<AIProviderFactory>()

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                commonModule(),
                module {
                    single<AIProvider<OpenAIChatCreateResponse>>(named("OpenAI")) { mockk<AIProvider<OpenAIChatCreateResponse>>(relaxed = true) }
                    single { AIProviderFactory() }
                }
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()  // Stop Koin after each test
    }

    @Test
    fun `test getProvider returns correct provider`() {
        val mockProvider: AIProvider<OpenAIChatCreateResponse> = factory.getProvider("OpenAI")

        assertEquals(mockProvider, factory.getProvider("OpenAI"))
    }

    @Test
    fun `test getProvider throws exception for unknown vendor`() {
        val factory = AIProviderFactory()
        assertFailsWith<IllegalArgumentException> {
            factory.getProvider<OpenAIChatCreateResponse>("UnknownVendor")
        }
    }
}
