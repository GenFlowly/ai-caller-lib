package com.genflowly.aicallerlib.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum representing the possible roles in the conversation.
 */
@Serializable
enum class Role {
    @SerialName("system") SYSTEM,
    @SerialName("user") USER,
    @SerialName("assistant") ASSISTANT
}
