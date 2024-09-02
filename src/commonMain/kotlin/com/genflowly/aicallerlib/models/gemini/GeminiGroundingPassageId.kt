package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * Identifier for a part within a GroundingPassage.
 * Source - https://ai.google.dev/api/generate-content#groundingpassageid
 *
 * @param passageId Output only. ID of the passage matching the GenerateAnswerRequest's
 * GroundingPassage.id.
 * @param partIndex Output only. Index of the part within the GenerateAnswerRequest's
 * GroundingPassage.content.
 */
@Serializable
data class GeminiGroundingPassageId(
    val passageId: String? = null,
    val partIndex: Int? = null
)
