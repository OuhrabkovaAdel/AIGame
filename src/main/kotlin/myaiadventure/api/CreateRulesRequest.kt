package com.example.myaiadventure.api

import com.example.myaiadventure.enums.ConsequenceSeverity
import com.example.myaiadventure.enums.Creativity
import com.example.myaiadventure.enums.Plausibility
import com.example.myaiadventure.enums.PlayerAgency
import com.example.myaiadventure.enums.StoryProgression
import kotlinx.serialization.Serializable

@Serializable
data class CreateRulesRequest(
    val storyProgression: StoryProgression,
    val playerAgency: PlayerAgency,
    val consequenceSeverity: ConsequenceSeverity,
    val plausibility: Plausibility,
    val creativity: Creativity
)
