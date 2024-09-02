package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.Function
import kotlinx.serialization.Serializable

/**
 * Structured representation of a function declaration as defined by the OpenAPI 3.03 specification.
 * Included in this declaration are the function name and parameters. This FunctionDeclaration is a
 * representation of a block of code that can be used as a Tool by the model and executed by the
 * client.
 *
 * Source - https://ai.google.dev/api/caching#functiondeclaration
 *
 * @param description A brief description of the function.
 * @param parameters Describes the parameters to this function. Reflects the Open API 3.03 Parameter
 * @param name The name of the function. Must be a-z, A-Z, 0-9, or contain underscores and dashes,
 * with a maximum length of 63.
 */
@Serializable
data class GeminiFunctionDeclaration(
    val description: String,
    val parameters: GeminiSchema,
    override val name: String
): Function()