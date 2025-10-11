
import com.genflowly.aicallerlib.clients.AIProvider
import com.genflowly.aicallerlib.models.AIRequest
import com.genflowly.aicallerlib.models.openai.OpenAIRequest
import com.genflowly.aicallerlib.models.openai.OpenAIResponse
import com.openai.client.OpenAIClient
import com.openai.models.responses.Response
import com.openai.models.responses.ResponseCreateParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KLogger


class OpenAIProvider(
    private val client: OpenAIClient,
    private val logger: KLogger
) : AIProvider<OpenAIResponse> {

    override suspend fun generateResponse(
        request: AIRequest
    ): OpenAIResponse = withContext(Dispatchers.IO) {
        require(request is OpenAIRequest) {
            "AIRequest must be of type OpenAIRequest"
        }

        val params: ResponseCreateParams = request.getParams()

        val response: Response = client.responses().create(params)

        OpenAIResponse(response)
    }
}