package com.example.myaiadventure.service

import com.example.myaiadventure.api.AiStoryRequest

interface AiService {
    fun generateStory(request: AiStoryRequest): String
}