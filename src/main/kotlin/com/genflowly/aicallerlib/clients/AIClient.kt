package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIRequest
import com.genflowly.aicallerlib.models.AIResponse

interface AIClient<T : AIResponse> {
    suspend fun generateResponse(request: AIRequest): T
}
