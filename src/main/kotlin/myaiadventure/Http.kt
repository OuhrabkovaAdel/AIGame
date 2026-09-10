package com.example.myaiadventure

import com.example.myaiadventure.exceptions.ApplicationException
import com.example.myaiadventure.exceptions.ErrorResponse
import com.example.myaiadventure.exceptions.toHttpStatus
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.respond

fun Application.configureHttp() {
    val log = this.log
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
            log.error("Unexpected application error", cause)
            call.respond(
                status = cause.errorCode.toHttpStatus(),
                message = ErrorResponse(
                    message = cause.message ?: "Invalid request",
                    code = cause.errorCode.text
                )
            )
        }
        exception<Exception> { call, cause ->
            log.error("Exception:",cause)
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

