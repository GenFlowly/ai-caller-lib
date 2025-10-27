package com.genflowly.aicallerlib.models.openai

import com.genflowly.aicallerlib.models.AIModelsListResponse
import com.openai.models.models.Model
import com.openai.models.models.ModelListPage

class OpenAIModelsListResponse(
    private val modelsListPage: ModelListPage? = null,
    private val filteredModels: List<Model>? = null
) : AIModelsListResponse {

    fun getModels(): List<Model> {
        return filteredModels ?: modelsListPage?.data().orEmpty()
    }

    override fun toString(): String {
        return "OpenAIModelsListResponse(models=${
            modelsListPage!!.data().map { it.id() }
        })"
    }

    companion object {
        fun fromPage(page: ModelListPage): OpenAIModelsListResponse {
            return OpenAIModelsListResponse(modelsListPage = page)
        }

        fun fromFilteredModels(models: List<Model>): OpenAIModelsListResponse {
            return OpenAIModelsListResponse(filteredModels = models)
        }
    }

}
