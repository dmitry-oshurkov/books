package name.oshurkov.books.api.swagger

import org.springframework.http.HttpHeaders.*
import org.springframework.http.HttpStatus.*
import org.springframework.web.servlet.function.*


val swaggerRoutes = router {

    GET("swagger-ui") {

        status(FOUND)
            .header(LOCATION, "/webjars/swagger-ui/index.html?url=/books.yaml&persistAuthorization=true&filter=true&tryItOutEnabled=false&layout=BaseLayout")
            .build()
    }
}
