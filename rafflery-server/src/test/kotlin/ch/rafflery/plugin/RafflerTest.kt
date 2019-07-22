package ch.rafflery.plugin

import ch.rafflery.assertions.shouldBe
import ch.rafflery.plugins.Raffler
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.junit.Test

internal class RafflerTest {
    private val raffler: Raffler = StubbedRafflerTest()

    @Test
    fun `can raffle a result`() {
        runBlocking {
            val result = async {
                raffler.draw(1337, 50)
            }

            var raffleResult = result.await()

            raffleResult?.id!! shouldBe "1337"
            raffleResult?.winnerSlot!! shouldBe 8
        }
    }

    private class StubbedRafflerTest : Raffler() {
        private val randomOrgResponse: String =
            ClassLoader.getSystemClassLoader().getResource("RandomOrgResponse.json")
                .readText(Charsets.UTF_8)

        override suspend fun request(requestJson: JSONObject): JSONObject {
            val parser = JSONParser()

            return parser.parse(randomOrgResponse) as JSONObject
        }
    }
}
