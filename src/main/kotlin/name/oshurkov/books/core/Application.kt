package name.oshurkov.books.core

import io.ktor.server.application.*
import io.ktor.server.netty.*
import name.oshurkov.books.*
import name.oshurkov.books.core.plugins.*


fun main(args: Array<String>) = EngineMain.main(args)


fun Application.module() {

    println(
"""    ____              __       
   / __ )____  ____  / /_______
  / __  / __ \/ __ \/ //_/ ___/
 / /_/ / /_/ / /_/ / ,< (__  ) 
/_____/\____/\____/_/|_/____/
:: Books :: $BUILD_VERSION :: © oshurkov.name
"""
    )

    configureData()
    configureHTTP()
    configureMonitoring()
    configureTemplating()
    configureSerialization()
    configureRouting()
}
