package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.AIResponse
import com.genflowly.aicallerlib.models.AIUsageMetadata
import com.google.genai.types.GenerateContentResponse

class GeminiResponse(response: GenerateContentResponse) : AIResponse {
    private val response: GenerateContentResponse

    init {
        this.response = response
    }

    override fun getText(): String? {
        return response.text()?.trim()
    }

    override fun getRawResponse(): GenerateContentResponse {
        return response
    }

    override fun getUsageMetadata(): AIUsageMetadata? {
        return response.usageMetadata().orElse(null)?.let { meta ->
            GeminiUsageMetadata(
                promptTokens = meta.promptTokenCount().orElse(null),
                completionTokens = meta.candidatesTokenCount().orElse(null),
                totalTokens = meta.totalTokenCount().orElse(null),
                cachedContentTokenCount = meta.cachedContentTokenCount().orElse(null),
                thoughtsTokenCount = meta.thoughtsTokenCount().orElse(null),
                toolUsePromptTokenCount = meta.toolUsePromptTokenCount().orElse(null),
                cacheTokensDetails = meta.cacheTokensDetails().orElse(null)
                    ?.map { GeminiModalityTokenCount(it.modality().orElse(null)?.toString(), it.tokenCount().orElse(null)) },
                candidatesTokensDetails = meta.candidatesTokensDetails().orElse(null)
                    ?.map { GeminiModalityTokenCount(it.modality().orElse(null)?.toString(), it.tokenCount().orElse(null)) },
                promptTokensDetails = meta.promptTokensDetails().orElse(null)
                    ?.map { GeminiModalityTokenCount(it.modality().orElse(null)?.toString(), it.tokenCount().orElse(null)) },
                toolUsePromptTokensDetails = meta.toolUsePromptTokensDetails().orElse(null)
                    ?.map { GeminiModalityTokenCount(it.modality().orElse(null)?.toString(), it.tokenCount().orElse(null)) },
                trafficType = meta.trafficType().orElse(null)?.toString()
            )
        }
    }

}