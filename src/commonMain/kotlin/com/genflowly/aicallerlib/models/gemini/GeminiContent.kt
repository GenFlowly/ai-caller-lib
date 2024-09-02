package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.Message
import com.genflowly.aicallerlib.models.Role
import kotlinx.serialization.Serializable

/**
 * The base structured datatype containing multi-part content of a message.
 * Source - https://ai.google.dev/api/caching#content
 *
 * @param parts Ordered Parts that constitute a single message. Parts may have different MIME types.
 * @param role The producer of the content. Must be either 'user' or 'model'.
 */
@Serializable
data class GeminiContent(
    val parts: List<GeminiPart>? = null,
    override val role: Role? = null
): Message<List<GeminiPart>>() {
    override val content = parts
}
