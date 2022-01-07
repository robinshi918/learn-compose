package com.example.megacompose.clouddrive

import androidx.lifecycle.ViewModel
import com.example.megacompose.domain.usecase.GetChildrenNodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import nz.mega.sdk.MegaNode
import javax.inject.Inject

/*
@HiltViewModel
class LoginViewModel @Inject internal constructor(
    val loginUseCase: LoginUseCase,
    val loginMfaUseCase: LoginMFAUseCase,
    val fetchNodesUseCase: FetchMegaNodesUseCase
) : ViewModel() {
 */


@HiltViewModel
class CloudDriveViewModel @Inject internal constructor(
    val getChildrenUseCase: GetChildrenNodsUseCase
) : ViewModel(){

    fun getMegaNodes(): List<MegaNode> {

        return listOf()
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