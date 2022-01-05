package com.example.megacompose.login.domain.usecase

import nz.mega.sdk.MegaApiAndroid

class LoginUseCase(private val megaApi: MegaApiAndroid) {
    suspend operator fun invoke(userName: String, password: String) {

    }
}