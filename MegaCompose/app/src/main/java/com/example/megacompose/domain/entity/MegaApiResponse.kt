package com.example.megacompose.domain.entity

import nz.mega.sdk.MegaApiJava
import nz.mega.sdk.MegaError
import nz.mega.sdk.MegaRequest

data class MegaApiResponse(
    val stage: MegaApiResponseStage,
    val api: MegaApiJava? = null,
    val request: MegaRequest? = null,
    val error: MegaError? = null
)

enum class MegaApiResponseStage {
    NONE,
    START,
    UPDATE,
    FINISH,
    TEMPORARY_ERROR
}