package com.example.myaiadventure.exceptions

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String,
    val code: String
)
