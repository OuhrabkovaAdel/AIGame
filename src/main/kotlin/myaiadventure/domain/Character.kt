package com.example.myaiadventure.domain

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val name:String?,
    val description: String?
)
