package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

@Serializable
abstract class Usage {
    abstract val promptTokens: Int?
    abstract val completionTokens: Int?
    abstract val totalTokens: Int?
}