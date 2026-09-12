package com.example.myaiadventure.api

import com.example.myaiadventure.api.StateChange
import kotlinx.serialization.Serializable

@Serializable
data class AiStoryResponse(
    val narration: String,
    val changes: List<StateChange>
)