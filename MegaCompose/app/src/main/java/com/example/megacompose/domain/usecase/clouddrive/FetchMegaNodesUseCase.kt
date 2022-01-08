package com.example.megacompose.domain.usecase.clouddrive

import nz.mega.sdk.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FetchMegaNodesUseCase @Inject internal constructor(val megaApi: MegaApiAndroid) {
    suspend operator fun invoke(): Int =
        suspendCoroutine { cont ->

            megaApi.fetchNodes(object : MegaRequestListenerInterface {
                override fun onRequestStart(api: MegaApiJava?, request: MegaRequest?) {
                    Timber.d("fetchNodes onRequestStart")
                }

                override fun onRequestUpdate(api: MegaApiJava?, request: MegaRequest?) {
                    Timber.d("fetchNodes onRequestUpdate")
                }

                override fun onRequestFinish(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    Timber.d("fetchNodes onRequestFinish: ${e!!.errorString}(${e.errorCode})")
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
                    Timber.d("fetchNodes onRequestTemporaryError")
                }
            })
        }
}