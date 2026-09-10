package com.example.myaiadventure.exceptions

import com.example.myaiadventure.enums.ErrorCodes
import io.ktor.http.HttpStatusCode

fun ErrorCodes.toHttpStatus(): HttpStatusCode =
    when (this) {
        ErrorCodes.INVALID_REQUEST -> HttpStatusCode.BadRequest
        ErrorCodes.STORY_NOT_FOUND -> HttpStatusCode.NotFound
        ErrorCodes.NOT_IMPLEMENTED -> HttpStatusCode.NotImplemented
    }
