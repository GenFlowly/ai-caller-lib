package com.genflowly.aicallerlib.models

import kotlinx.serialization.Serializable

/**
 * URI based data defined in Gemini.
 * Source - https://ai.google.dev/api/caching#filedata
 *
 * @param mimeType The IANA standard MIME type of the source data.
 * @param fileUri URI.
 */
@Serializable
data class FileData(
    val mimeType: String? = null,
    val fileUri: String
)
