package com.example.megacompose

import android.app.Application
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import dagger.hilt.android.HiltAndroidApp
import nz.mega.sdk.MegaApiAndroid
import timber.log.Timber

@HiltAndroidApp
class MegaComposeApplication : Application() {
    companion object {
        val APP_KEY = "XqBVhaKb"
        val USER_AGENT = "MegaComposeDemoAndroid"


        lateinit var INSTANCE: MegaComposeApplication

        fun getMegaApi(): MegaApiAndroid {
            val packageInfo: PackageInfo
            var path: String? = null

            try {
                packageInfo = INSTANCE.packageManager.getPackageInfo(INSTANCE.packageName, 0)
                path = packageInfo.applicationInfo.dataDir + "/"
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return MegaApiAndroid(APP_KEY, USER_AGENT, path)
        }
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        //TODO use another Timber Tree for Release build
        Timber.plant(Timber.DebugTree())
    }

}