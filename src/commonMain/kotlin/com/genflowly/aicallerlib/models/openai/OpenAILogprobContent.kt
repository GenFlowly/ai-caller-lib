package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

@Serializable
data class OpenAILogprobContent(
    val token: String,
    val logprob: Int,
    val bytes: Array<Byte>
)
