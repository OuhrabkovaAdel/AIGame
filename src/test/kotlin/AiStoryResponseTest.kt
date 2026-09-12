package com.example.myaiadventure.api

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import myaiadventure.api.CreateCharacterData
import myaiadventure.enums.StateChangeType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AiStoryResponseTest {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    @Test
    fun `test AiStoryResponse deserialization`() {
        val jsonString = """
            {
                "narration": "You enter the dark forest and notice a stranger watching you from the shadows.",
                "changes": [
                    {
                        "type": "CREATE_CHARACTER",
                        "character": {
                            "name": "Aldren",
                            "description": "A mysterious traveler wearing a dark cloak."
                        }
                    }
                ]
            }
        """.trimIndent()

        val response = json.decodeFromString<AiStoryResponse>(jsonString)

        assertEquals("You enter the dark forest and notice a stranger watching you from the shadows.", response.narration)
        assertEquals(1, response.changes.size)
        assertEquals("Aldren", response.changes[0].character?.name)
        assertEquals("A mysterious traveler wearing a dark cloak.", response.changes[0].character?.description)
        assertEquals(StateChangeType.CREATE_CHARACTER, response.changes[0].type)
        assertEquals(response, json.decodeFromString<AiStoryResponse>(json.encodeToString(response)))
    }

    @Test
    fun `test AiStoryResponse deserialization with empty changes list`() {
        val jsonString = """
            {
                "narration": "You enter the dark forest and notice a stranger watching you from the shadows.",
                "changes": []
            }
        """.trimIndent()

        val response = json.decodeFromString<AiStoryResponse>(jsonString)

        assertEquals("You enter the dark forest and notice a stranger watching you from the shadows.", response.narration)
        assertEquals(0, response.changes.size)
        assertEquals(response, json.decodeFromString<AiStoryResponse>(json.encodeToString(response)))
    }

    @Test
    fun `test AiStoryResponse deserialization fails when narration is missing`() {
        val jsonString = """
        {
            "changes": []
        }
    """.trimIndent()

    assertFailsWith<SerializationException> {
        json.decodeFromString<AiStoryResponse>(jsonString)
    }
    }

    @Test
    fun `test AiStoryResponse serialization`() {
        val response = AiStoryResponse(
            narration = "You enter the dark forest and notice a stranger watching you from the shadows.",
            changes = listOf(
                StateChange(
                    type = StateChangeType.CREATE_CHARACTER,
                    character = CreateCharacterData(
                        name = "Aldren",
                        description = "A mysterious traveler wearing a dark cloak."
                    )
                )
            )
        )

        val jsonString = json.encodeToString(response)

        val decoded = json.decodeFromString<AiStoryResponse>(jsonString)
        assertEquals(response.narration, decoded.narration)
        assertEquals(response.changes.size, decoded.changes.size)
        assertEquals("Aldren", decoded.changes[0].character?.name)
        assertEquals("A mysterious traveler wearing a dark cloak.", decoded.changes[0].character?.description)
        assertEquals(StateChangeType.CREATE_CHARACTER, decoded.changes[0].type)
    }
}
