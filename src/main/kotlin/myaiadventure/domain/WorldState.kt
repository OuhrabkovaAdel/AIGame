package com.example.myaiadventure.domain

import kotlinx.serialization.Serializable


data class WorldState(
    val characters: List<Character>?,
    val environment: String?,
    val currentEvent: String?
)
