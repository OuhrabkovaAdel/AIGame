package com.example.myaiadventure

import com.example.myaiadventure.exeptions.ApplicationException
import com.example.myaiadventure.exeptions.ErrorResponse
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.respond

fun Application.configureHttp() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader("MyCustomHeader")
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
    install(StatusPages){
        exception<ApplicationException> { call, cause ->
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = ErrorResponse(
                    message = cause.message ?: "Invalid request",
                    code = "INVALID_REQUEST"
                )
            )
        }
        exception<Exception> { call, cause ->
            call.respond(
                status = HttpStatusCode.InternalServerError,
                message = ErrorResponse(
                    message = "Internal server error",
                    code = "INTERNAL_ERROR"
                )
            )
        }
    }
}
