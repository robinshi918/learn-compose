package com.example.megacompose.domain.usecase.clouddrive

import nz.mega.sdk.MegaApiAndroid
import nz.mega.sdk.MegaNode
import javax.inject.Inject

class GetChildrenOfRootUseCase @Inject internal constructor(val megaApi: MegaApiAndroid) {
    operator fun invoke(): List<MegaNode> {
        return megaApi.getChildren(megaApi.rootNode)
    }
}