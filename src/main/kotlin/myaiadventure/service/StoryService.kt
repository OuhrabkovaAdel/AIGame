package myaiadventure.service

import com.example.myaiadventure.api.AiStoryRequest
import com.example.myaiadventure.api.CreateStoryRequest
import com.example.myaiadventure.api.CreateStoryResponse
import com.example.myaiadventure.domain.StoryState
import com.example.myaiadventure.domain.StoryTurn
import com.example.myaiadventure.domain.WorldState
import com.example.myaiadventure.exeptions.InvalidStoryRequestException
import com.example.myaiadventure.service.AiService
import myaiadventure.domain.Story
import java.util.UUID

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
            generalSetting = createStoryRequest.generalSetting
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
            throw InvalidStoryRequestException("Genres list cannot be empty")
        }
        if (createStoryRequest.genres.size > 3) {
            throw InvalidStoryRequestException("Genres list cannot exceed 3 items")
        }
        if (createStoryRequest.writingStyles.size > 3) {
            throw InvalidStoryRequestException("Writing styles list cannot exceed 3 items")
        }
        if (createStoryRequest.title.isBlank()) {
            throw InvalidStoryRequestException("Title cannot be empty")
        }
        if (createStoryRequest.generalSetting.isBlank()) {
            throw InvalidStoryRequestException("General setting cannot be empty")
        }
    }

    /* fun processAction(
         storyId: String,
         action: String
     ): StoryTurn {

         val state = stories[storyId]?: throw IllegalArgumentException("Story $storyId not found")

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
         return turn
     }

     fun getStory(storyId: String): StoryState? {
         return stories[storyId]
     }*/
}
