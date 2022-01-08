package com.example.megacompose.domain.usecase.clouddrive

import nz.mega.sdk.MegaApiAndroid
import nz.mega.sdk.MegaNode
import javax.inject.Inject

class GoToParentFolder @Inject internal constructor(val megaApi: MegaApiAndroid) {

    /**
     * return the children
     */
    operator fun invoke(node: MegaNode): List<MegaNode>? {
        return if (node.type == MegaNode.TYPE_ROOT) {
            null
        } else {
            val parentNode = megaApi.getNodeByHandle(node.parentHandle)
            megaApi.getChildren(parentNode)
        }
    }
}