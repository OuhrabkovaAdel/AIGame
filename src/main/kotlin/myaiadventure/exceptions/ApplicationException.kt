package com.example.myaiadventure.exceptions

import com.example.myaiadventure.enums.ErrorCodes
import io.ktor.http.*

class ApplicationException(
    message: String,
    val errorCode: ErrorCodes
) : Exception(message)