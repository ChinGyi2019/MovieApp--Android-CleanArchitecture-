package com.chingyi.moviedbappcleanarchitecture.di.module

import com.chingyi.cache.source.PopularMovieCacheSourceImpl
import com.chingyi.cache.source.UpComingMovieCacheSourceImpl
import com.chingyi.common.popular.movie.PopularMovieCacheSource
import com.chingyi.common.popular.movie.PopularMovieRepositoryImpl
import com.chingyi.common.upcoming.UpcomingMovieCacheSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import room.db.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule{

    @Singleton
    @Provides
    fun upComingCacheSource(db : AppDatabase): UpcomingMovieCacheSource {
        return UpComingMovieCacheSourceImpl(db = db)
    }

    @Singleton
    @Provides
    fun popularCacheSource(db : AppDatabase): PopularMovieCacheSource {
        return PopularMovieCacheSourceImpl(db = db)
    }

}