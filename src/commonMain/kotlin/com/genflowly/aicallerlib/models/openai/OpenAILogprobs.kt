package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

@Serializable
data class OpenAILogprobs(
    val content: OpenAILogprobContent
)
