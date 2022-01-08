package com.example.megacompose.clouddrive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megacompose.domain.usecase.GetChildrenNodesUseCase
import com.example.megacompose.domain.usecase.GetChildrenOfRootUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import nz.mega.sdk.MegaNode
import javax.inject.Inject

@HiltViewModel
class CloudDriveViewModel @Inject internal constructor(
    val getChildrenUseCase: GetChildrenNodesUseCase,
    val getChildrenOfRootUseCase: GetChildrenOfRootUseCase
) : ViewModel() {

    private val _nodeList = MutableLiveData<List<MegaNode>>(listOf())
    val nodeList: LiveData<List<MegaNode>> = _nodeList

    private val _currentParent = MutableLiveData<MegaNode>()

    fun getChildren(parent: MegaNode): List<MegaNode>? {
        return getChildrenUseCase(parent)
    }

    fun getChildrenOfRoot(): List<MegaNode> {
        return getChildrenOfRootUseCase()
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