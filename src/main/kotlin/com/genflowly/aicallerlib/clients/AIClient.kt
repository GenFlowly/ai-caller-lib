package com.genflowly.aicallerlib.clients

import com.genflowly.aicallerlib.models.AIModelsListResponse
import com.genflowly.aicallerlib.models.AIRequest
import com.genflowly.aicallerlib.models.AIResponse

interface AIClient<T : AIResponse, U: AIModelsListResponse> {
    suspend fun generateResponse(request: AIRequest): T
    suspend fun generateResponseStream(request: AIRequest): kotlinx.coroutines.flow.Flow<T>
    suspend fun listAllModels(): U
    suspend fun listTextGenerationModels(): U
}
