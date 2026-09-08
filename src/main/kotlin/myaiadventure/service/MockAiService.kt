package com.example.myaiadventure.service

import com.example.myaiadventure.api.AiStoryRequest

class MockAiService : AiService {

    override fun generateStory(request: AiStoryRequest): String {
        return "Uděláš: \"${request.action}\". Les kolem tebe na chvíli ztichne."
    }
}