package com.example.myaiadventure.api

import kotlinx.serialization.Serializable

@Serializable
data class PlayerAction(
    val action: String
)