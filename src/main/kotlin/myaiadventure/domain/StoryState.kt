package com.example.myaiadventure.domain

import kotlinx.serialization.Serializable
import myaiadventure.domain.Story


data class StoryState(
    val story: Story,
    val worldState: WorldState,
    val turns: List<StoryTurn> = emptyList(),
)