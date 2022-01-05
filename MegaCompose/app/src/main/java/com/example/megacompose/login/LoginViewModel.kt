package com.example.megacompose.login

import androidx.lifecycle.ViewModel
import com.example.megacompose.login.domain.entity.MegaApiResponse
import com.example.megacompose.login.domain.entity.MegaApiResponseStage.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import nz.mega.sdk.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject internal constructor(val megaApi: MegaApiAndroid) : ViewModel() {

    fun login(user: String, password: String): Flow<MegaApiResponse> =
        callbackFlow {
            megaApi.login(user, password, object : MegaRequestListenerInterface {
                override fun onRequestStart(api: MegaApiJava?, request: MegaRequest?) {
                    Timber.d("onRequestStart user($user)")
                    trySend(MegaApiResponse(START, api, request))
                }

                override fun onRequestUpdate(api: MegaApiJava?, request: MegaRequest?) {
                    Timber.d("onRequestUpdate")
                    trySend(MegaApiResponse(UPDATE, api, request))
                }

                override fun onRequestFinish(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    Timber.d("onRequestFinish ${e?.errorCode}(${e?.errorString})")
                    trySend(MegaApiResponse(FINISH, api, request, e))
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
                    trySend(MegaApiResponse(TEMPORARY_ERROR, api, request, e))
                }
            })

            awaitClose()
        }

    fun login(user: String, password: String, authCode: String) {
        megaApi.multiFactorAuthLogin(
            user,
            password,
            authCode,
            object : MegaRequestListenerInterface {
                override fun onRequestStart(api: MegaApiJava?, request: MegaRequest?) {
                    TODO("Not yet implemented")
                }

                override fun onRequestUpdate(api: MegaApiJava?, request: MegaRequest?) {
                    TODO("Not yet implemented")
                }

                override fun onRequestFinish(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    TODO("Not yet implemented")
                }

                override fun onRequestTemporaryError(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    TODO("Not yet implemented")
                }

            })
    }

}