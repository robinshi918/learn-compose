package com.example.megacompose.domain.usecase

import com.example.megacompose.domain.entity.MegaApiResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import nz.mega.sdk.MegaApiAndroid
import javax.inject.Inject

class GetChildrenNodsUseCase @Inject internal constructor(val megaApi: MegaApiAndroid) {
    suspend operator fun invoke() : Flow<MegaApiResponse>  = callbackFlow {
        awaitClose()
    }
}