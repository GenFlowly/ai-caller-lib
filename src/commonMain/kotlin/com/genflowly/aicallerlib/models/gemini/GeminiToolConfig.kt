package com.genflowly.aicallerlib.models.gemini

import kotlinx.serialization.Serializable

/**
 * The Tool configuration containing parameters for specifying Tool use in the request.
 * Source - https://ai.google.dev/api/caching#toolconfig
 *
 * @param functionCallingConfig Function calling config.
 */
@Serializable
data class GeminiToolConfig(
    val functionCallingConfig: GeminiFunctionCallingConfig? = null
)
