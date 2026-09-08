package com.example.myaiadventure.domain

import kotlinx.serialization.Serializable

@Serializable
data class StoryTurn(
    val storyId: String,
    val playerAction: String?,
    val aiResponse: String?
)