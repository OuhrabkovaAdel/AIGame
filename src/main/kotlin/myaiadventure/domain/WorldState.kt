package com.example.myaiadventure.domain


data class WorldState(
    val characters: List<Character>,
    val environment: String,
    val currentEvent: String
)
