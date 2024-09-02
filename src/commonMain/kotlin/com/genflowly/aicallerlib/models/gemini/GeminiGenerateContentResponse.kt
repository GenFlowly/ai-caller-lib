package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Response from the model supporting multiple candidate responses.
 * Source - https://ai.google.dev/api/generate-content#generatecontentresponse
 *
 * @param candidates Candidate responses from the model.
 * @param promptFeedback Returns the prompt's feedback related to the content filters.
 * @param usageMetadata Output only. Metadata on the generation requests' token usage.
 */
@Serializable
data class GeminiGenerateContentResponse(
    val candidates: List<GeminiCandidate>,
    val promptFeedback: GeminiPromptFeedback? = null,
    val usageMetadata: GeminiUsageMetadata
)
