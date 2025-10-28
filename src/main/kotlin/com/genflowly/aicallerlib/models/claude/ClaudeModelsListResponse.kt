package com.genflowly.aicallerlib.models.claude

import com.anthropic.models.models.ModelInfo
import com.anthropic.models.models.ModelListPage
import com.genflowly.aicallerlib.models.AIModelsListResponse

class ClaudeModelsListResponse(
    private val modelListPage: ModelListPage
) : AIModelsListResponse {

    fun getModels(): List<ModelInfo> = modelListPage.data()

    override fun toList(): List<String> {
        return modelListPage.data().map { it.id() }
    }

    override fun toString(): String {
        return "ClaudeModelsListResponse(models=${modelListPage.data().map { it.id() }})"
    }
}
