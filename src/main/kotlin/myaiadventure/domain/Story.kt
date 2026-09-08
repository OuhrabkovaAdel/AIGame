package myaiadventure.domain

import com.example.myaiadventure.enums.Genre
import com.example.myaiadventure.enums.MaturityLevel
import com.example.myaiadventure.enums.WritingStyle
import java.util.UUID


data class Story(
    val id: UUID,
    val title: String,
    val genres: List<Genre>,
    val tags: List<String>,
    val maturityLevel: MaturityLevel,
    val writingStyles: List<WritingStyle>,
    val generalSetting: String
)