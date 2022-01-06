package com.example.megacompose.login.domain.usecase

import nz.mega.sdk.MegaApiAndroid
import javax.inject.Inject

class LoginMFAUseCase @Inject constructor(private val megaApi: MegaApiAndroid) {
    suspend operator fun invoke(userName: String, password: String, authCode: String) {

    }
}