package com.genflowly.aicallerlib.models

interface AIResponse {

    fun getText(): String?

    fun getRawResponse(): Any

    /**
     * Returns token usage metadata for this response, or null if not available.
     * For streaming responses, only the final chunk typically carries usage data.
     */
    fun getUsageMetadata(): AIUsageMetadata? = null

}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> AIResponse.rawAs(): T = this.getRawResponse() as T
