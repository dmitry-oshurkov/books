package name.oshurkov.books.core.plugins

import io.ktor.server.application.*
import io.ktor.server.thymeleaf.*
import org.thymeleaf.templateresolver.*
import kotlin.text.Charsets.UTF_8


fun Application.configureTemplating() {

    install(Thymeleaf) {
        setTemplateResolver(
            ClassLoaderTemplateResolver().apply {
                prefix = "templates/thymeleaf/"
                suffix = ".html"
                characterEncoding = UTF_8.name()
            }
        )
    }
}
