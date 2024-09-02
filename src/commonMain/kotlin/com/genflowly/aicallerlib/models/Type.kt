package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

/**
 * Type contains the list of OpenAPI data types as defined by
 * https://spec.openapis.org/oas/v3.0.3#data-types
 */

@Serializable
enum class Type {
    TYPE_UNSPECIFIED,
    STRING,
    NUMBER,
    INTEGER,
    BOOLEAN,
    ARRAY,
    OBJECT
}