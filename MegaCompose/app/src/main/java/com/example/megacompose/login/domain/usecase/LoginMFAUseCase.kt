package com.example.megacompose.login.domain.usecase

import nz.mega.sdk.MegaApiAndroid


class LoginMFAUseCase(private val megaApi: MegaApiAndroid) {
    suspend operator fun invoke(userName: String, password: String, authCode: String) {

    }
}