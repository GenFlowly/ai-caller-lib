package com.genflowly.aicallerlib.models.openai

import com.genflowly.aicallerlib.models.AIRequest
import com.openai.models.responses.ResponseCreateParams


class OpenAIRequest(private val params: ResponseCreateParams) : AIRequest {

    override fun toString(): String {
        return "OpenAIRequest{" +
                "params=" + params +
                '}'
    }

    fun getParams(): ResponseCreateParams {
        return params
    }
}