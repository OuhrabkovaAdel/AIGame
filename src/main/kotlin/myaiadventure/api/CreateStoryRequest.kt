package com.example.myaiadventure.api

import com.example.myaiadventure.enums.Genre
import com.example.myaiadventure.enums.MaturityLevel
import com.example.myaiadventure.enums.WritingStyle
import kotlinx.serialization.Serializable

@Serializable
data class CreateStoryRequest(
    val title: String,
    val genres: List<Genre>,
    val tags: List<String>,
    val maturityLevel: MaturityLevel,
    val writingStyles: List<WritingStyle>,
    val generalSetting: String
)