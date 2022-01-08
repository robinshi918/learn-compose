package com.example.megacompose.domain.usecase

import nz.mega.sdk.MegaApiAndroid
import javax.inject.Inject

class LoginMFAUseCase @Inject constructor(private val megaApi: MegaApiAndroid) {
    suspend operator fun invoke(userName: String, password: String, authCode: String) {
        TODO("not yet implemented")
    }


    /*fun login(user: String, password: String, authCode: String) {
        megaApi.multiFactorAuthLogin(
            user,
            password,
            authCode,
            object : MegaRequestListenerInterface {
                override fun onRequestStart(api: MegaApiJava?, request: MegaRequest?) {
                    TODO("Not yet implemented")
                }

                override fun onRequestUpdate(api: MegaApiJava?, request: MegaRequest?) {
                    TODO("Not yet implemented")
                }

                override fun onRequestFinish(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    TODO("Not yet implemented")
                }

                override fun onRequestTemporaryError(
                    api: MegaApiJava?,
                    request: MegaRequest?,
                    e: MegaError?
                ) {
                    TODO("Not yet implemented")
                }

            })
    }*/
}