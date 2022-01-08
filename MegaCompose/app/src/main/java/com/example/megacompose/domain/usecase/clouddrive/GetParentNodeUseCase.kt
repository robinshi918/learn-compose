package com.example.megacompose.domain.usecase.clouddrive

import nz.mega.sdk.MegaApiAndroid
import nz.mega.sdk.MegaNode
import timber.log.Timber
import javax.inject.Inject

class GetParentNodeUseCase @Inject internal constructor(val megaApi: MegaApiAndroid) {
    operator fun invoke(node: MegaNode): MegaNode {
        if (node.type == MegaNode.TYPE_ROOT) {
            return node
        }
        val result = megaApi.getNodeByHandle(node.parentHandle)
        Timber.d("Parent of ${node.name} --> ${result.name}")
        return result
    }
}