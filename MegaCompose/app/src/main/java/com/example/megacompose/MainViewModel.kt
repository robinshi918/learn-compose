package com.example.megacompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megacompose.domain.entity.MegaApiResponse
import com.example.megacompose.domain.entity.MegaApiResponseStage
import com.example.megacompose.domain.usecase.clouddrive.FetchMegaNodesUseCase
import com.example.megacompose.domain.usecase.clouddrive.GetChildrenUseCase
import com.example.megacompose.domain.usecase.clouddrive.GetParentNodeUseCase
import com.example.megacompose.domain.usecase.clouddrive.GetRootNodeUseCase
import com.example.megacompose.domain.usecase.login.LoginMFAUseCase
import com.example.megacompose.domain.usecase.login.LoginUseCase
import com.example.megacompose.login.API_NONE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nz.mega.sdk.MegaError
import nz.mega.sdk.MegaNode
import nz.mega.sdk.MegaNode.TYPE_ROOT
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject internal constructor(
    val loginUseCase: LoginUseCase,
    val loginMfaUseCase: LoginMFAUseCase,
    val fetchNodesUseCase: FetchMegaNodesUseCase,
    val getChildrenUseCase: GetChildrenUseCase,
    val getParentNodeUseCase: GetParentNodeUseCase,
    val getRootNodeUseCase: GetRootNodeUseCase
) : ViewModel() {

    // Login Screen
    private val _loginResult: MutableLiveData<Int> = MutableLiveData(API_NONE)
    val loginResult: LiveData<Int> = _loginResult

    private val _loginStage: MutableLiveData<MegaApiResponseStage> =
        MutableLiveData(MegaApiResponseStage.NONE)
    val loginStage: LiveData<MegaApiResponseStage> = _loginStage

    // Cloud Drive Screen
    private val _cloudDriveNodeList = MutableLiveData<List<MegaNode>>(listOf())
    val cloudDriveNodeList: LiveData<List<MegaNode>> = _cloudDriveNodeList
    private val _cloudDriveTitle = MutableLiveData("CLOUD DRIVE")
    val cloudDriveTitle: LiveData<String> = _cloudDriveTitle
    private val _cloudDriveIsRoot = MutableLiveData(true)
    val cloudDriveIsRoot: LiveData<Boolean> = _cloudDriveIsRoot
    lateinit var currentParent: MegaNode

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            loginUseCase(userName, password).collect { resp ->
                Timber.d("receive login responses - ${resp.stage} ${resp.stage}")

                when (resp.stage) {
                    MegaApiResponseStage.NONE -> {
                        _loginStage.value = resp.stage
                    }
                    MegaApiResponseStage.START -> {
                        _loginStage.value = resp.stage
                    }
                    MegaApiResponseStage.UPDATE -> {
                        _loginStage.value = resp.stage
                    }
                    MegaApiResponseStage.FINISH -> {
                        _loginStage.value = resp.stage
                        handleLoginFinish(resp)
                    }
                    MegaApiResponseStage.TEMPORARY_ERROR -> {
                    }
                }


            }
        }
    }

    private suspend fun handleLoginFinish(resp: MegaApiResponse) {
        if (resp.error != null) {
            when (resp.error.errorCode) {
                MegaError.API_OK -> {
                    val fetchNodeResult = fetchNodesUseCase()

                    val root = getRootNodeUseCase()
                    root?.let { getChildren(root) }
                    //                            getChildrenOfRoot()

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

    /**
     * login with MFA
     */
    fun login(userName: String, password: String, authCode: String) {
        TODO("not yet implemented")
    }

    fun getChildren(parent: MegaNode) {
        val nodeList = getChildrenUseCase(parent)
        if (nodeList != null) {
            currentParent = parent
            _cloudDriveNodeList.value = nodeList
        }

        setTitleBar(parent)
    }

    /*fun getChildrenOfRoot() {
        _cloudDriveNodeList.value = getChildrenOfRootUseCase()
        _cloudDriveIsRoot.value = true
        _cloudDriveTitle.value = "CLOUD DRIVE"
    }*/

    // FIXME known bug: Cannot go back to root folder  if go into 2 subfolders,
    fun gotoParentFolder() {
        if (currentParent.type != TYPE_ROOT) {
            val parent = getParentNodeUseCase(currentParent)
            Timber.d("gotoParentFolder() ${currentParent.name} --> ${parent.name}")
            _cloudDriveNodeList.value = getChildrenUseCase(parent)

            for (node in _cloudDriveNodeList.value!!) {
                Timber.d("listing files: ${node.name}")
            }
            setTitleBar(parent)
        }
    }

    private fun setTitleBar(parent: MegaNode) {
        if (parent.type == TYPE_ROOT) {
            _cloudDriveTitle.value = "CLOUD DRIVE"
            _cloudDriveIsRoot.value = true
        } else {
            _cloudDriveTitle.value = parent.name
            _cloudDriveIsRoot.value = false
        }
    }

}