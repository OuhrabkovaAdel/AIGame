package myaiadventure.api

import kotlinx.serialization.Serializable

@Serializable
data class CreateCharacterData(
    val name: String,
    val description: String
)