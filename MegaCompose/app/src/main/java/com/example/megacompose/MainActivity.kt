package com.example.megacompose

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.megacompose.login.LoginViewModel
import com.example.megacompose.ui.theme.MegaComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import nz.mega.sdk.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MegaComposeTheme {
                MainScreen(navController = navController, loginViewModel)
            }
        }
    }

    private fun callMegaApi() {
        val userName = "rsh+7@mega.co.nz"
        val password = "hellohello123@"
        Handler().postDelayed(
            {
                Log.d("Robin", "before calling getMetaApi()")
                val megaApi = MegaComposeApplication.getMegaApi()
                Log.d("Robin", "before calling MegaAPI.login()")
                megaApi.login(
                    userName,
                    password,
                    object : MegaRequestListenerInterface {
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
                            request?.let {
                                if (request.type == MegaRequest.TYPE_LOGIN && e!!.errorCode == MegaError.API_OK) {
                                    megaApi.fetchNodes(object : MegaRequestListenerInterface {
                                        override fun onRequestStart(
                                            api: MegaApiJava?,
                                            request: MegaRequest?
                                        ) {}

                                        override fun onRequestUpdate(
                                            api: MegaApiJava?,
                                            request: MegaRequest?
                                        ) {}

                                        override fun onRequestFinish(
                                            api: MegaApiJava?,
                                            request: MegaRequest?,
                                            e: MegaError?
                                        ) {
                                            Log.e(
                                                "Robin",
                                                "fetchnode onRequestFinish() e = ${e!!.errorCode}(${e!!.errorString})"
                                            )
                                            if (request!!.type == MegaRequest.TYPE_FETCH_NODES &&
                                                e.errorCode == MegaError.API_OK
                                            ) {
                                                Log.e("Robin", "megaapi.fetchNodes ok")

                                                val nodeList = megaApi.getChildren(megaApi.rootNode)
                                                for (node in nodeList) {
                                                    Log.d(
                                                        "Robin",
                                                        "Node name = ${node.name}\tNode Type = ${
                                                            nodeTypeString(
                                                                node.type
                                                            )
                                                        }"
                                                    )
                                                }

                                            }

                                        }

                                        override fun onRequestTemporaryError(
                                            api: MegaApiJava?,
                                            request: MegaRequest?,
                                            e: MegaError?
                                        ) {
                                        }
                                    })
                                }
                            }
                        }

                        override fun onRequestTemporaryError(
                            api: MegaApiJava?,
                            request: MegaRequest?,
                            e: MegaError?
                        ) {
                            Log.d("Robin", "Login onRequestTemporaryError()")
                            e?.let {
                                Log.d("Robin", "${e.errorCode}(${e.errorString})")
                            }
                        }
                    })


            }, 1000
        )
    }
}

fun nodeTypeString(type: Int): String {
    return when (type) {
        MegaNode.TYPE_FILE -> "FILE"
        MegaNode.TYPE_FOLDER -> "FOLDER"
        MegaNode.TYPE_ROOT -> "ROOT"
        MegaNode.TYPE_INCOMING -> "INCOMING"
        MegaNode.TYPE_RUBBISH -> "RUBBISH"
        else -> "unknown"
    }
}



