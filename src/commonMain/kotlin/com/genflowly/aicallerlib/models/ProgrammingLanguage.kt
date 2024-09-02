package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

/**
 * Supported programming languages for the generated code defined in Gemini.
 * Source - https://ai.google.dev/api/caching#language
 */
@Serializable
enum class ProgrammingLanguage {
    LANGUAGE_UNSPECIFIED,
    PYTHON
}