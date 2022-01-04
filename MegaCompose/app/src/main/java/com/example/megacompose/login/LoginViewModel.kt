package com.example.megacompose.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nz.mega.sdk.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject internal constructor(val megaApi: MegaApiAndroid) : ViewModel() {

    fun login(user: String, password: String) {
        megaApi.login(user, password, object : MegaRequestListenerInterface {
            override fun onRequestStart(api: MegaApiJava?, request: MegaRequest?) {
                TODO("Not yet implemented")
            }

            override fun onRequestUpdate(api: MegaApiJava?, request: MegaRequest?) {
                TODO("Not yet implemented")
            }

            override fun onRequestFinish(api: MegaApiJava?, request: MegaRequest?, e: MegaError?) {
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
    }

    fun login(user: String, password: String, authCode: String) {
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
    }

}