package com.example.myaiadventure

import com.example.myaiadventure.api.HealthResponse
import com.example.myaiadventure.service.AiService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import myaiadventure.api.storyRoutes
import myaiadventure.service.StoryService

fun Application.configureRouting(aiService: AiService) {
    val storyService = StoryService(aiService)

    routing {

        get("/") {
            call.respondText("Hello, World!")
        }

        get("/api/health") {
            call.respond(
                HealthResponse(status = "ok")
            )
        }

        storyRoutes(storyService)
    }
}
