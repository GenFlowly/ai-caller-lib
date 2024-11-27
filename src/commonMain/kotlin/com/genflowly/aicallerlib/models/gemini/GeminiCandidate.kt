package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.CandidateResponse
import com.genflowly.aicallerlib.models.AIResponse
import kotlinx.serialization.Serializable

/**
 * A response candidate generated from the model.
 * Source - https://ai.google.dev/api/generate-content#candidate
 *
 * @param safetyRatings List of ratings for the safety of a response candidate.
 * @param citationMetadata Output only. Citation information for model-generated candidate.
 * @param tokenCount Output only. Token count for this candidate.
 * @param groundingAttributions Output only. Attribution information for sources that contributed to
 * a grounded answer.
 * @param index Output only. Index of the candidate in the list of response candidates.
 * @param content Output only. Generated content returned from the model.
 * @param finishReason Output only. The reason why the model stopped generating tokens.
 */
@Serializable
data class GeminiCandidate(
    val safetyRatings: List<GeminiSafetyRating>? = null,
    val citationMetadata: GeminiCitationMetadata? = null,
    val tokenCount: Int,
    val groundingAttributions: GeminiGroundingAttribution? = null,
    val index: Int? = null,
    override val content: GeminiContent,
    override val finishReason: GeminiFinishReason
): CandidateResponse<GeminiContent, GeminiFinishReason>(), AIResponse
