package com.genflowly.aicallerlib.models

data class AIRequestConfig(
    val model: String,
    override val role: Role = Role.USER,
    override val content: String? = null,
    override val stopSequences: List<String>? = null,
    override val candidateCount: Int? = null,
    override val temperature: Double? = null,
    override val topP: Double? = null,
    override val maxOutputTokens: Int? = null
): Message<String>(), GenerationConfigurable
