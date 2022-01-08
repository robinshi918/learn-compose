package com.example.megacompose.domain.usecase.clouddrive

import nz.mega.sdk.MegaApiAndroid
import nz.mega.sdk.MegaNode
import javax.inject.Inject

class GetRootNodeUseCase @Inject internal constructor(val megaApi: MegaApiAndroid) {
    operator fun invoke(): MegaNode? = megaApi.rootNode
}