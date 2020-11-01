package name.oshurkov.books

import com.fasterxml.jackson.annotation.JsonInclude.Include.*
import com.fasterxml.jackson.databind.DeserializationFeature.*
import org.springframework.boot.autoconfigure.jackson.*
import org.springframework.context.annotation.*

@Configuration
class JacksonConfiguration {

    @Bean
    fun configure() = Jackson2ObjectMapperBuilderCustomizer {

        it.serializationInclusion(NON_NULL)
        it.featuresToDisable(FAIL_ON_UNKNOWN_PROPERTIES)
    }
}
