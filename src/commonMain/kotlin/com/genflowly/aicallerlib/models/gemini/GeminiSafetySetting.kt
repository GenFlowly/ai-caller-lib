package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.HarmBlockThreshold
import com.genflowly.aicallerlib.models.HarmCategory
import kotlinx.serialization.Serializable

/**
 * Safety setting, affecting the safety-blocking behavior.
 * Source - https://ai.google.dev/api/generate-content#safetysetting
 *
 * @param threshold The category for this setting.
 * @param category Controls the probability threshold at which harm is blocked.
 */
@Serializable
data class GeminiSafetySetting(
    val threshold: HarmBlockThreshold,
    override val category: HarmCategory
): GeminiSafety()
