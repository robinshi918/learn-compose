package com.example.megacompose.di

import com.example.megacompose.MegaComposeApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.mega.sdk.MegaApiAndroid
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMegaApi(): MegaApiAndroid {
        return MegaComposeApplication.getMegaApi()
    }




}