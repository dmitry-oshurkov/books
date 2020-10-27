package name.oshurkov.books

import org.springframework.context.annotation.*
import org.springframework.web.filter.*

@Configuration
class RequestLoggingFilterConfig {
    @Bean
    fun logFilter() = CommonsRequestLoggingFilter().apply {
        setIncludeQueryString(true)
        setIncludePayload(true)
        setMaxPayloadLength(10000)
        setIncludeHeaders(false)
        setAfterMessagePrefix("REQUEST DATA : ")
    }
}
