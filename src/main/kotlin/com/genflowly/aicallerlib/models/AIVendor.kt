package com.genflowly.aicallerlib.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class AIVendor {
    @SerialName("OPENAI") OPENAI,
    @SerialName("GEMINI") GEMINI,
    @SerialName("CLAUDE") CLAUDE,
    @SerialName("MODEL") MODEL,
}
