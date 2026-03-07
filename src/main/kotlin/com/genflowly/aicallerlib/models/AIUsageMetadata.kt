package com.genflowly.aicallerlib.models

/**
 * Marker interface for provider-specific usage metadata returned after a generation request.
 * Cast to the concrete type ({@link GeminiUsageMetadata}, {@link OpenAIUsageMetadata},
 * {@link ClaudeUsageMetadata}) to access provider-specific fields.
 */
interface AIUsageMetadata {
    fun getInputTokenCount(): Long?
    fun getOutputTokenCount(): Long?
    fun getTotalTokenCount(): Long?
    fun getTokenCountReadFromCache(): Long?
}
