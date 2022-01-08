package com.example.megacompose.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megacompose.domain.entity.MegaApiResponseStage
import com.example.megacompose.domain.usecase.clouddrive.FetchMegaNodesUseCase
import com.example.megacompose.domain.usecase.login.LoginMFAUseCase
import com.example.megacompose.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nz.mega.sdk.MegaError
import timber.log.Timber
import javax.inject.Inject

const val API_NONE = 1

@HiltViewModel
class LoginViewModel @Inject internal constructor(
    val loginUseCase: LoginUseCase,
    val loginMfaUseCase: LoginMFAUseCase,
    val fetchNodesUseCase: FetchMegaNodesUseCase
) : ViewModel() {

    private val _result: MutableLiveData<Int> = MutableLiveData(API_NONE)
    val result: LiveData<Int> = _result

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            loginUseCase(userName, password).collect { resp ->
                Timber.d("receive log responses - ${resp.stage} ${resp.stage}")
                if (resp.stage == MegaApiResponseStage.FINISH && resp.error != null) {
                    when (resp.error.errorCode) {
                        MegaError.API_OK -> {
                            val fetchNodeResult = fetchNodesUseCase()
                            Timber.d("login OK. fetchNodes is Done with $fetchNodeResult")
                            _result.value = fetchNodeResult
                        }
                        MegaError.API_EMFAREQUIRED -> {
                            //TODO show MFA UI
                            _result.value = resp.error.errorCode
                        }
                        MegaError.API_EARGS,
                        MegaError.API_EFAILED,
                        MegaError.API_ENOENT -> {
                            //TODO notify UI to show error message
                            _result.value = resp.error.errorCode
                        }
                    }
                }
            }
        }
    }

    /**
     * login with MFA
     */
    fun login(userName: String, password: String, authCode: String) {
        TODO("not yet implemented")
    }
}

