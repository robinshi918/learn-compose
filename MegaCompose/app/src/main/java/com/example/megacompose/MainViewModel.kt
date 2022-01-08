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

    // Login
    private val _loginResult: MutableLiveData<Int> = MutableLiveData(API_NONE)
    val loginResult: LiveData<Int> = _loginResult

    // Cloud Drive
    private val _cloudDriveNodeList = MutableLiveData<List<MegaNode>>(listOf())
    val cloudDriveNodeList: LiveData<List<MegaNode>> = _cloudDriveNodeList
    private val _currentParentNode = MutableLiveData<MegaNode>()
    val currentParentNode: LiveData<MegaNode> = _currentParentNode

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            loginUseCase(userName, password).collect { resp ->
                Timber.d("receive login responses - ${resp.stage} ${resp.stage}")
                if (resp.stage == MegaApiResponseStage.FINISH && resp.error != null) {
                    when (resp.error.errorCode) {
                        MegaError.API_OK -> {
                            val fetchNodeResult = fetchNodesUseCase()
                            getChildrenOfRoot()

                            // after fetching root nodes, notify UI to leave Login Screen
                            _loginResult.value = fetchNodeResult
                            Timber.d("login OK. fetchNodes is Done with $fetchNodeResult")
                        }
                        MegaError.API_EMFAREQUIRED -> {
                            //TODO show MFA UI
                            _loginResult.value = resp.error.errorCode
                        }
                        MegaError.API_EARGS,
                        MegaError.API_EFAILED,
                        MegaError.API_ENOENT -> {
                            //TODO notify UI to show error message
                            _loginResult.value = resp.error.errorCode
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

    fun getChildren(parent: MegaNode) {
        val nodeList = getChildrenNodesUseCase(parent)
        if (nodeList != null) {
            _cloudDriveNodeList.value = nodeList
            _currentParentNode.value = parent
        }
    }

    fun getChildrenOfRoot() {
        _cloudDriveNodeList.value =  getChildrenOfRootUseCase()
    }
}