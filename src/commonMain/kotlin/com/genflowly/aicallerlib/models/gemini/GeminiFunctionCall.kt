package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.Function
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * A predicted FunctionCall returned from the model that contains a string representing the
 * FunctionDeclaration.name with the arguments and their values.
 * Source - https://ai.google.dev/api/caching#functioncall
 *
 * @param name The name of the function to call. Must be a-z, A-Z, 0-9, or contain underscores and
 * dashes, with a maximum length of 63.
 * @param args The function parameters and values in JSON object format.
 */
@Serializable
data class GeminiFunctionCall(
    val args: JsonObject? = null,
    override val name: String
): Function()
