package com.example.megacompose

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.megacompose.ui.theme.MegaComposeTheme
import nz.mega.sdk.MegaApiJava
import nz.mega.sdk.MegaError
import nz.mega.sdk.MegaRequest
import nz.mega.sdk.MegaRequestListenerInterface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MegaComposeTheme {
                MainScreen()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed(
            {
                val megaApi = MegaComposeApplication.getMegaApi()
                megaApi.login("rsh+5@mega.co.nz", "hello123123@", object :
                    MegaRequestListenerInterface {
                    override fun onRequestStart(api: MegaApiJava?, request: MegaRequest?) {
                        Log.d("Robin", "Login onRequestStart()")
                    }

                    override fun onRequestUpdate(api: MegaApiJava?, request: MegaRequest?) {
                        Log.d("Robin", "Login onRequestUpdate()")
                    }

                    override fun onRequestFinish(
                        api: MegaApiJava?,
                        request: MegaRequest?,
                        e: MegaError?
                    ) {
                        Log.d("Robin", "Login onRequestFinish()")
                        e?.let {
                            Log.d("Robin", "${e.errorCode}(${e.errorString})")
                        }
                    }

                    override fun onRequestTemporaryError(
                        api: MegaApiJava?,
                        request: MegaRequest?,
                        e: MegaError?
                    ) {
                        Log.d("Robin", "Login onRequestTemporaryError()")
                        e?.let {
                            Log.d("Robin","${e.errorCode}(${e.errorString})")
                        }
                    }
                })


            }, 1000
        )
    }
}



