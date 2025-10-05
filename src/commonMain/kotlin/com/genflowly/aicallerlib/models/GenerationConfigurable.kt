package com.genflowly.aicallerlib.models

interface GenerationConfigurable {
    val stopSequences: List<String>?
    val candidateCount: Int?
    val temperature: Double?
    val topP: Double?
    val maxOutputTokens: Int?
    val topK: Double?
}