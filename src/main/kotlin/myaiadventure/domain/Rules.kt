package com.example.myaiadventure.domain

import com.example.myaiadventure.enums.ConsequenceSeverity
import com.example.myaiadventure.enums.Creativity
import com.example.myaiadventure.enums.Plausibility
import com.example.myaiadventure.enums.PlayerAgency
import com.example.myaiadventure.enums.StoryProgression

data class Rules(
    val storyProgression: StoryProgression,
    val playerAgency: PlayerAgency,
    val consequenceSeverity: ConsequenceSeverity,
    val plausibility: Plausibility,
    val creativity: Creativity
)