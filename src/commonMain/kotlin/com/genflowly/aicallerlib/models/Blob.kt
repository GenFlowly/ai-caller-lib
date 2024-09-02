package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

/**
 * Raw media bytes.
 * Source - https://ai.google.dev/api/caching#blob
 *
 * @param mimeType The IANA standard MIME type of the source data.
 * @param data Raw bytes for media formats. A base64-encoded string.
 */
@Serializable
data class Blob(
    val mimeType: String,
    val data: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Blob

        if (mimeType != other.mimeType) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mimeType.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}
