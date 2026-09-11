package myaiadventure.service

import com.example.myaiadventure.api.CreateRulesRequest
import com.example.myaiadventure.api.CreateStoryRequest
import com.example.myaiadventure.api.CreateStoryResponse
import com.example.myaiadventure.api.GetStoryResponse
import com.example.myaiadventure.domain.Rules
import com.example.myaiadventure.domain.StoryState
import com.example.myaiadventure.domain.WorldState
import com.example.myaiadventure.enums.ErrorCodes
import com.example.myaiadventure.exceptions.ApplicationException
import com.example.myaiadventure.service.AiService
import myaiadventure.domain.Story
import java.util.UUID
import kotlin.String

class StoryService(
    private val aiService: AiService
) {

    private val stories = mutableMapOf<UUID, StoryState>()

    fun createStory(createStoryRequest: CreateStoryRequest): CreateStoryResponse {

        validateCreateStoryRequest(createStoryRequest)
        val story = Story(
            id = UUID.randomUUID(),
            title = createStoryRequest.title,
            genres = createStoryRequest.genres,
            tags = createStoryRequest.tags,
            maturityLevel = createStoryRequest.maturityLevel,
            writingStyles = createStoryRequest.writingStyles,
            worldSetting = createStoryRequest.worldSetting,
            storyPremise = createStoryRequest.storyPremise,
            focus = createStoryRequest.focus,
            narrativeRules = Rules(
                storyProgression = createStoryRequest.rules.storyProgression,
                playerAgency = createStoryRequest.rules.playerAgency,
                consequenceSeverity = createStoryRequest.rules.consequenceSeverity,
                plausibility = createStoryRequest.rules.plausibility,
                creativity = createStoryRequest.rules.creativity
            )
        )
        val worldState = WorldState(
            characters = emptyList(),
            environment = "Unknown",
            currentEvent = "Story beginning"
        )

        stories[story.id] = StoryState(story = story, worldState = worldState
        )
        return CreateStoryResponse(
            id = story.id.toString()
        )
    }

    private fun validateCreateStoryRequest(createStoryRequest: CreateStoryRequest) {
        if (createStoryRequest.genres.isEmpty()) {
            throw ApplicationException("Genres list cannot be empty", ErrorCodes.INVALID_REQUEST)
        }
        if (createStoryRequest.genres.size > 3) {
            throw ApplicationException("Genres list cannot exceed 3 items", ErrorCodes.INVALID_REQUEST)
        }
        if (createStoryRequest.writingStyles.size > 3) {
            throw ApplicationException("Writing styles list cannot exceed 3 items", ErrorCodes.INVALID_REQUEST)
        }
        if (createStoryRequest.title.isBlank()) {
            throw ApplicationException("Title cannot be empty", ErrorCodes.INVALID_REQUEST)
        }
        if (createStoryRequest.worldSetting.isBlank()) {
            throw ApplicationException("World setting cannot be empty", ErrorCodes.INVALID_REQUEST)
        }
        if (createStoryRequest.focus.isEmpty()) {
            throw ApplicationException("Focus list cannot be empty", ErrorCodes.INVALID_REQUEST)
        }
        if (createStoryRequest.focus.size > 3) {
            throw ApplicationException("Focus list cannot exceed 3 items", ErrorCodes.INVALID_REQUEST)
        }
        if (createStoryRequest.storyPremise.isBlank()) {
            throw ApplicationException("Story premise cannot be empty", ErrorCodes.INVALID_REQUEST)
        }
    }

     fun getStory(
         storyId: String,
     ): GetStoryResponse {

         val storyUuid = try {
             UUID.fromString(storyId)
         } catch (e: IllegalArgumentException) {
             throw ApplicationException("Invalid storyId format", ErrorCodes.INVALID_REQUEST)
         }

         val storyState = stories[storyUuid]
             ?: throw ApplicationException(
                 "Story $storyId not found",
                 ErrorCodes.STORY_NOT_FOUND
             )
         val story = storyState.story

         return GetStoryResponse(
             id = story.id.toString(),
             title = story.title,
             genres = story.genres,
             tags = story.tags,
             maturityLevel = story.maturityLevel,
             writingStyles = story.writingStyles,
             generalSetting = story.worldSetting
         )
     }
}
/*
val AITextResponce = aiService.generateStory(
    AiStoryRequest(
        storyText = state.story.text,
        history = state.turns.map { it.text },
        action = action
    )
)

val turn = StoryTurn(
    storyId = storyId,
    action = action,
    text = AITextResponce
)
         stories[storyId] = state.copy(
             turns = state.turns + turn
         )

*/
