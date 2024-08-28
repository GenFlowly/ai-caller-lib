package com.genflowly.aicallerlib.models
import kotlinx.serialization.Serializable

/**
 * Represents a single message in the conversation.
 *
 * @param role The role of the message author. Can be "system", "user", or "assistant".
 * @param content The content of the message.
 * @param name The name of the user in the conversation. This is optional and can be used to distinguish between different users.
 */
@Serializable
data class Message(
    val role: Role,
    val content: String,
    val name: String? = null
)
