package com.example.myaiadventure.domain

import kotlinx.serialization.Serializable
import myaiadventure.domain.Story

@Serializable
data class StoryState(
    val story: Story,
    val turns: List<StoryTurn> = emptyList()
)