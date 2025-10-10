package com.genflowly.aicallerlib.models

interface AIResponse<T>  {

    fun getText(): String?

    fun getRawResponse(): T

}
