package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.HarmCategory
import com.genflowly.aicallerlib.models.HarmProbability
import kotlinx.serialization.Serializable

@Serializable
data class GeminiSafetySetting(
    val probability: HarmProbability,
    val blocked: Boolean,
    override val category: HarmCategory
): GeminiSafety()
