package com.chingyi.network.di

import android.app.Application
import android.content.Context
import com.chingyi.network.api.MovieApi
import com.example.netwrok.di.RetrofitProvider
import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
object ServiceModule{

        @Provides
        @Singleton
        fun provideMyApi(context: Context): MovieApi {
            return RetrofitProvider.retrofit(context).create(MovieApi::class.java)
        }

}


