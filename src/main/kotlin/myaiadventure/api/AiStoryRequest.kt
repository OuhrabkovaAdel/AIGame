package com.example.myaiadventure.api

data class AiStoryRequest(
    val storyText: String,
    val history: List<String>,
    val action: String
)