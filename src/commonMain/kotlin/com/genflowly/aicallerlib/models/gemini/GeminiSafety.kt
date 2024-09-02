package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.HarmCategory
import kotlinx.serialization.Serializable

@Serializable
abstract class GeminiSafety {
    abstract val category: HarmCategory
}