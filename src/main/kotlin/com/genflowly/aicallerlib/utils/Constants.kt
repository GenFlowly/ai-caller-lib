package com.genflowly.aicallerlib.utils

object Constants {
    // Openai Constants
    const val OPENAI_BASE_API = "https://api.openai.com"
    const val OPENAI_CHAT_ENDPOINT = "/v1/chat/completions"
    const val OPENAI = "OpenAI"

    // Gemini Constants
    const val GOOGLE_GEMINI = "GoogleGemini"
    const val GEMINI_BASE_API = "https://generativelanguage.googleapis.com/v1beta"
    const val GEMINI_GENERATE_CONTENT_ENDPOINT = "/models/{model}:generateContent"
}