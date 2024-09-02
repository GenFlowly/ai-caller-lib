package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.Type
import kotlinx.serialization.Serializable

/**
 * The Schema object allows the definition of input and output data types. These types can be
 * objects, but also primitives and arrays. Represents a select subset of an OpenAPI 3.0 schema
 * object.
 *
 * Source - https://ai.google.dev/api/caching#schema
 *
 * @param type Data type.
 * @param format The format of the data. This is used only for primitive datatypes.
 * @param description A brief description of the parameter. This could contain examples of use.
 * @param nullable Indicates if the value may be null.
 * @param enum Possible values of the element of Type.STRING with enum format.
 * @param maxItems Maximum number of the elements for Type.ARRAY.
 * @param properties Properties of Type.OBJECT.
 * @param required Required properties of Type.OBJECT.
 * @param items Schema of the elements of Type.ARRAY.
 */

@Serializable
data class GeminiSchema(
    val type: Type,
    val format: String? = null,
    val description: String? = null,
    val nullable: Boolean? = null,
    val enum: List<String>? = null,
    val maxItems: String? = null,
    val properties: HashMap<String, GeminiSchema>? = null,
    val required: List<String>? = null,
    val items: GeminiSchema? = null
)
