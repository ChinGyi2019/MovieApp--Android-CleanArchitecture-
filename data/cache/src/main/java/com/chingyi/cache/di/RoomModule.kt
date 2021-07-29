package com.chingyi.cache.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import room.db.AppDatabase
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getInstance(application)

}