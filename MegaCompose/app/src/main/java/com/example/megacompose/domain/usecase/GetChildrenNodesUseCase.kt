package com.example.megacompose.domain.usecase

import nz.mega.sdk.MegaApiAndroid
import nz.mega.sdk.MegaNode
import javax.inject.Inject

class GetChildrenNodesUseCase @Inject internal constructor(val megaApi: MegaApiAndroid) {
    operator fun invoke(parent: MegaNode): List<MegaNode> {
        // assume megaApi.getChildren() is synchronous call
        val result = megaApi.getChildren(parent)

        if (result != null) {
            return result
        } else {
            throw IllegalArgumentException(
                "GetChildrenNodsUseCase failed. parent does not exist or is not a folder"
            )
        }
    }
}