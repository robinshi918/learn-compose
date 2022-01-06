package com.example.megacompose.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megacompose.login.domain.entity.MegaApiResponseStage
import com.example.megacompose.login.domain.usecase.LoginMFAUseCase
import com.example.megacompose.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nz.mega.sdk.*
import timber.log.Timber
import javax.inject.Inject

const val API_NONE = 1

@HiltViewModel
class LoginViewModel @Inject internal constructor(
    val megaApi: MegaApiAndroid,
    val loginUseCase: LoginUseCase,
    val loginMfaUseCase: LoginMFAUseCase
) : ViewModel() {

//    var result: Int by mutableStateOf(API_NONE)

    private val _result: MutableLiveData<Int> = MutableLiveData(API_NONE)

    val result: LiveData<Int> = _result

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            loginUseCase(userName, password).collect { resp ->
                Timber.d("receive log responses - ${resp.stage} ${resp.stage}")
                if (resp.stage == MegaApiResponseStage.FINISH) {
                    _result.value = if (resp.error!!.errorCode == MegaError.API_OK) {
                        MegaError.API_OK
                    } else {
                        // TODO login error or show MFA
                        resp.error.errorCode
                    }
                }
            }
        }

    }


    /*fun login(user: String, password: String): Flow<MegaApiResponse> =
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
        }*/

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

fun nodeTypeString(type: Int): String {
    return when (type) {
        MegaNode.TYPE_FILE -> "FILE"
        MegaNode.TYPE_FOLDER -> "FOLDER"
        MegaNode.TYPE_ROOT -> "ROOT"
        MegaNode.TYPE_INCOMING -> "INCOMING"
        MegaNode.TYPE_RUBBISH -> "RUBBISH"
        else -> "unknown"
    }
}