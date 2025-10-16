package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequest
import com.genflowly.aicallerlib.models.gemini.GeminiRequest
import com.genflowly.aicallerlib.models.gemini.GeminiResponse
import com.google.genai.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KLogger

class GeminiProxyClient(
    private val client: Client,
    private val logger: KLogger
) : AIClient<GeminiResponse> {
    
    override suspend fun generateResponse(
        request: AIRequest
    ): GeminiResponse = withContext(Dispatchers.IO) {
        require(request is GeminiRequest) {
            "AIRequest must be of type GeminiRequest"
        }

        val response = client.models.generateContent(
            request.getRequest().model().get(),
            request.getRequest().contents().get()[0].text(),
            null
        )


        GeminiResponse(response)
    }
}