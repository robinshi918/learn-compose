package com.example.megacompose.domain.usecase.login

import com.example.megacompose.domain.entity.MegaApiResponse
import com.example.megacompose.domain.entity.MegaApiResponseStage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import nz.mega.sdk.*
import timber.log.Timber
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val megaApi: MegaApiAndroid) {
    suspend operator fun invoke(user: String, password: String): Flow<MegaApiResponse> =
        callbackFlow {
            megaApi.login(user, password, object : MegaRequestListenerInterface {
                override fun onRequestStart(api: MegaApiJava?, request: MegaRequest?) {
                    Timber.d("onRequestStart user($user)")
                    trySend(MegaApiResponse(MegaApiResponseStage.START, api, request))
                }

                override fun onRequestUpdate(api: MegaApiJava?, request: MegaRequest?) {
                    Timber.d("onRequestUpdate")
                    trySend(MegaApiResponse(MegaApiResponseStage.UPDATE, api, request))
                }

                override fun onRequestFinish(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    Timber.d("onRequestFinish ${e?.errorCode}(${e?.errorString})")
                    trySend(MegaApiResponse(MegaApiResponseStage.FINISH, api, request, e))
                    request?.let {
                        e?.let {
                            when (e.errorCode) {
                                MegaError.API_OK -> {
                                    //TODO notify UI to navigate to home page
                                }
                                MegaError.API_EFAILED,
                                MegaError.API_EARGS,
                                MegaError.API_ENOENT -> {
                                    //TODO notify UI to show error message
                                }
                                MegaError.API_EMFAREQUIRED -> {
                                    //TODO show MFA UI
                                }
                                else -> {
                                }
                            }
                        }
                    }
                }

                override fun onRequestTemporaryError(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    Timber.w("onRequestTemporaryError error(${e?.errorCode}) = ${e?.errorString}")
                    trySend(MegaApiResponse(MegaApiResponseStage.TEMPORARY_ERROR, api, request, e))
                }
            })

            awaitClose()
        }
}