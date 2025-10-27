package com.genflowly.aicallerlib.models.gemini

import com.genflowly.aicallerlib.models.AIModelsListResponse
import com.google.genai.Pager
import com.google.genai.types.Model

class GeminiModelsListResponse(
    private val modelPager: Pager<Model>? = null,
    private val filteredModels: List<Model>? = null
) : AIModelsListResponse {

    fun getModels(): List<Model> {
        return filteredModels ?: modelPager?.toList().orEmpty()
    }

    fun getCleanModelNames(): List<String> {
        return getModels().mapNotNull { it.cleanName() }
    }

    override fun toString(): String {
        val models = getCleanModelNames()
        return "GeminiModelsListResponse(models=$models)"
    }

    companion object {
        fun fromPager(pager: Pager<Model>): GeminiModelsListResponse {
            return GeminiModelsListResponse(modelPager = pager)
        }

        fun fromFilteredModels(models: List<Model>): GeminiModelsListResponse {
            return GeminiModelsListResponse(filteredModels = models)
        }
    }
}

private fun Model.cleanName(): String? {
    val raw = this.name().orElse(null) ?: return null
    return raw.removePrefix("models/")
}
