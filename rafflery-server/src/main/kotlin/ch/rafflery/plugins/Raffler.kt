package ch.rafflery.plugins

import ch.rafflery.domain.raffle.RaffleResult
import ch.rafflery.infrastructure.Config
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.url
import org.json.simple.JSONArray
import org.json.simple.JSONObject

/**
 * Uses random.org to get a RaffleResult
 */
open class Raffler {
    private val RANDOM_ORG_API_URL = Config.rafflerConfig.rafflerApi
    private val RANDOM_ORG_API_KEY = Config.rafflerConfig.rafflerApiKey

    open suspend fun draw(id: Int, maxSlots: Int): RaffleResult? {
        val requestJson = createRequestJson(id, maxSlots)

        val responseJson = request(requestJson)

        val raffleId = responseJson.get("id")
        val randomResult = responseJson.get("result") as JSONObject
        val randomResultData = (randomResult.get("random") as JSONObject).get("data") as JSONArray
        val signature = randomResult.get("signature")

        return RaffleResult(
            raffleId.toString(),
            signature.toString(),
            (randomResultData[0] as Long).toInt()
        )
    }

    open suspend fun request(requestJson: JSONObject): JSONObject {

        val response = HttpClient().use { client ->
            client.post<JSONObject> {
                url(RANDOM_ORG_API_URL)
                body = requestJson.toString()
            }
        }

        return response
    }

    private fun createRequestJson(id: Int, maxSlots: Int): JSONObject {
        val raffleJson = JSONObject()
        raffleJson.put("jsonrpc", "2.0")
        raffleJson.put("method", "generateSignedIntegers")

        val params = JSONObject()
        params.put("apiKey", RANDOM_ORG_API_KEY)
        params.put("n", 1)
        params.put("min", 1)
        params.put("max", maxSlots)
        params.put("replacement", false)

        raffleJson.put("params", params)
        raffleJson.put("id", id)

        return raffleJson
    }
}
