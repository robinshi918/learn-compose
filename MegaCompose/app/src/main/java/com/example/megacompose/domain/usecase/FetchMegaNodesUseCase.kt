package com.example.megacompose.domain.usecase

import com.example.megacompose.domain.entity.MegaApiResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import nz.mega.sdk.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FetchMegaNodesUseCase @Inject internal constructor(val megaApi: MegaApiAndroid) {
    suspend operator fun invoke(): Int =
        suspendCoroutine { cont ->

            megaApi.fetchNodes(object : MegaRequestListenerInterface {
                override fun onRequestStart(api: MegaApiJava?, request: MegaRequest?) {}

                override fun onRequestUpdate(api: MegaApiJava?, request: MegaRequest?) {}

                override fun onRequestFinish(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    if (e != null) {
                        cont.resumeWith(Result.success(e.errorCode))
                    } else {
                        cont.resumeWith(Result.success(MegaError.API_EFAILED))
                    }
                }

                override fun onRequestTemporaryError(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                }
            })
        }
}