package com.genflowly.aicallerlib.models.openai

import kotlinx.serialization.SerialName

abstract class OpenAIChatCreate {
    abstract val model: String
    @SerialName("service_tier") abstract val serviceTier: String?
}