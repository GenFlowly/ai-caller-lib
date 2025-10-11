package com.genflowly.aicallerlib.models

interface AIResponse  {

    fun getText(): String?

    fun getRawResponse(): Any

}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> AIResponse.rawAs(): T = this.getRawResponse() as T
