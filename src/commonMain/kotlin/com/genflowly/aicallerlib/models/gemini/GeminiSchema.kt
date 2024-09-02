package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

/**
 * The Schema object allows the definition of input and output data types. These types can be
 * objects, but also primitives and arrays. Represents a select subset of an OpenAPI 3.0 schema
 * object.
 *
 * Reference - https://ai.google.dev/api/caching#schema
 *
 * @param type Data type.
 * @param format The format of the data. This is used only for primitive datatypes.
 * Supported formats: for NUMBER type: float, double for INTEGER type: int32,
 * int64 for STRING type: enum
 * @param description A brief description of the parameter. This could contain examples of use.
 * Parameter description may be formatted as Markdown.
 * @param nullable Indicates if the value may be null.
 * @param enum Possible values of the element of Type.STRING with enum format. For example we can
 * define an Enum Direction as : {type:STRING, format:enum, enum:["EAST", NORTH", "SOUTH", "WEST"]}
 * @param maxItems Maximum number of the elements for Type.ARRAY.
 * @param properties Properties of Type.OBJECT.
 * @param required Required properties of Type.OBJECT.
 * @param items Schema of the elements of Type.ARRAY.
 */

@Serializable
data class GeminiSchema(
    val type: Type,
    val format: String? = null,
    val nullable: Boolean? = null,
    val enum: Array<String>? = null,
    val maxItems: String? = null,
    val properties: HashMap<String, GeminiSchema>? = null,
    val required: Array<String>? = null,
    val items: GeminiSchema? = null
)
