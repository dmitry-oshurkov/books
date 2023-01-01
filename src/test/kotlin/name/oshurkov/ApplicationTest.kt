package name.oshurkov

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import name.oshurkov.books.plugins.*
import kotlin.test.*


class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/about").apply {
            assertEquals(status, HttpStatusCode.OK)
            assertEquals(bodyAsText(), BUILD_VERSION)
        }
    }
}
