package com.example.megacompose.domain.usecase.clouddrive

import nz.mega.sdk.MegaApiAndroid
import nz.mega.sdk.MegaNode
import javax.inject.Inject

class GetChildrenUseCase @Inject internal constructor(val megaApi: MegaApiAndroid) {
    operator fun invoke(parent: MegaNode): List<MegaNode>? {
        // assume megaApi.getChildren() is synchronous call
        return megaApi.getChildren(parent)
    }
}