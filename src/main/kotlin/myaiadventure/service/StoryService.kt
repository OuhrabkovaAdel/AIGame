package myaiadventure.service

import com.example.myaiadventure.domain.StoryState
import com.example.myaiadventure.domain.StoryTurn
import myaiadventure.domain.Story

class StoryService {

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

        val turn = StoryTurn(
            storyId = storyId,
            action = action,
            text = "Uděláš: \"$action\". Les kolem tebe na chvíli ztichne."
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
