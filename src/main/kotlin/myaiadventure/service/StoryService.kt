package myaiadventure.service

import com.example.myaiadventure.api.AiStoryRequest
import com.example.myaiadventure.domain.StoryState
import com.example.myaiadventure.domain.StoryTurn
import com.example.myaiadventure.service.AiService
import myaiadventure.domain.Story

class StoryService(
    private val aiService: AiService
) {

    private val stories = mutableMapOf<String, StoryState>()

    fun createStory(title: String): Story {
        val story = Story(
            id = "story-1",
            title = title,
            text = "Stojíš na kraji temného lesa. Před tebou vede úzká cesta mezi stromy."
        )
        stories[story.id] = StoryState(story = story)
        return story
    }

    fun processAction(
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
    }
}
