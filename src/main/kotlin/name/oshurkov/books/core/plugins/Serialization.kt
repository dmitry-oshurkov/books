package name.oshurkov.books.core.plugins

import com.fasterxml.jackson.annotation.JsonInclude.Include.*
import com.fasterxml.jackson.databind.SerializationFeature.*
import com.fasterxml.jackson.dataformat.xml.*
import com.fasterxml.jackson.datatype.jsr310.*
import com.fasterxml.jackson.module.kotlin.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*


fun Application.configureSerialization() {
    install(ContentNegotiation) { jackson() }
}


fun Any.toXml() = xmlMapper.writeValueAsString(this)!!


private val xmlMapper = XmlMapper()
    .registerKotlinModule()
    .registerModule(JavaTimeModule())
    .setSerializationInclusion(NON_NULL)
    .disable(WRITE_DATES_AS_TIMESTAMPS)
