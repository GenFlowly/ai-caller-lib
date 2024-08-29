package com.genflowly.aicallerlib.models

/**
 * Abstract class to represent a single message in the conversation.
 *
 * @param role The role of the message author. Can be "system", "user", or "assistant".
 * @param content The content of the message.
 */
abstract class Message {
    abstract val role: Role
    abstract val content: String?
}