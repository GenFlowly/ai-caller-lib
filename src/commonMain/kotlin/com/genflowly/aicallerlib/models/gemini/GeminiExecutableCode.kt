package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

@Serializable
data class GeminiExecutableCode(
    val language: ProgrammingLanguage,
    val code: String
)
