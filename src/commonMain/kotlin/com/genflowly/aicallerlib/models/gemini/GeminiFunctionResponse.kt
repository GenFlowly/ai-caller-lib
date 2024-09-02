package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.Function
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * The result output from a FunctionCall that contains a string representing the
 * FunctionDeclaration.name and a structured JSON object containing any output from the function is
 * used as context to the model.
 * Source - https://ai.google.dev/api/caching#functionresponse
 *
 * @param The name of the function to call. Must be a-z, A-Z, 0-9, or contain underscores and
 * dashes, with a maximum length of 63.
 * @param response The function response in JSON object format.
 */
@Serializable
data class GeminiFunctionResponse(
    val response: JsonObject,
    override val name: String
): Function()
