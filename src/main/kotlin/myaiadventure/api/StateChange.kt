package com.example.myaiadventure.api

import kotlinx.serialization.Serializable
import myaiadventure.api.CreateCharacterData
import myaiadventure.enums.StateChangeType

@Serializable
data class StateChange(
    val type: StateChangeType,
    val character: CreateCharacterData?
)