package com.example.myaiadventure.api

import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(
    val status: String
)
