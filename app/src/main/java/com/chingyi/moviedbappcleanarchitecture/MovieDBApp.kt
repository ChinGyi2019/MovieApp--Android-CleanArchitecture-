package com.chingyi.moviedbappcleanarchitecture

import android.app.Application
import androidx.databinding.library.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MovieDBApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
           Timber.plant(Timber.DebugTree())
        }
    }
}