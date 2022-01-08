package com.example.megacompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megacompose.domain.entity.MegaApiResponseStage
import com.example.megacompose.domain.usecase.*
import com.example.megacompose.login.API_NONE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nz.mega.sdk.MegaError
import nz.mega.sdk.MegaNode
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject internal constructor(
    val loginUseCase: LoginUseCase,
    val loginMfaUseCase: LoginMFAUseCase,
    val fetchNodesUseCase: FetchMegaNodesUseCase,
    val getChildrenNodesUseCase: GetChildrenNodesUseCase,
    val getChildrenOfRootUseCase: GetChildrenOfRootUseCase
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

    private val _nodeList = MutableLiveData<List<MegaNode>>(listOf())
    val nodeList: LiveData<List<MegaNode>> = _nodeList

    private val _currentParentNode = MutableLiveData<MegaNode>()
    val currentParentNode: LiveData<MegaNode> = _currentParentNode

    fun getChildren(parent: MegaNode): List<MegaNode> {
        return getChildrenNodesUseCase(parent)
    }

    fun getChildrenOfRoot(): List<MegaNode> {
        return getChildrenOfRootUseCase()
    }

}