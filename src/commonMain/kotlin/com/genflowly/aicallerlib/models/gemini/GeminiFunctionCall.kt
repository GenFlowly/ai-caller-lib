package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class GeminiFunctionCall(
    val args: JsonObject? = null,
    override val name: String
): Function()
