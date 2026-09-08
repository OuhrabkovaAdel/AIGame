package com.example.myaiadventure

import com.example.myaiadventure.service.MockAiService
import io.ktor.server.application.*
import com.example.myaiadventure.service.AiService

fun Application.start() {
    configureHttp()
    configureSerialization()
    configureRouting(getAiService())
}

private fun getAiService(): AiService {
    return MockAiService()
}