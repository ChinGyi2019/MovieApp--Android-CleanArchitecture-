package com.chingyi.moviedbappcleanarchitecture.di.module

import com.chingyi.common.genre.GenreNetworkSource
import com.chingyi.common.popular.movie.PopularMovieNetworkSource
import com.chingyi.common.popular.movie.PopularMovieRepositoryImpl
import com.chingyi.common.popular.series.PopularSeriesNetworkSource
import com.chingyi.common.upcoming.UpComingMovieNetworkSource
import com.chingyi.network.api.MovieApi
import com.chingyi.network.source.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideUpComingMovieNetworkSource(api : MovieApi): UpComingMovieNetworkSource {
        return UpComingMovieNetworkSourceImpl(api = api)
    }

    @Singleton
    @Provides
    fun providePopularMovieNetworkSource(api : MovieApi): PopularMovieNetworkSource {
        return PopularMovieNetworkSourceImpl(api = api)
    }

    @Singleton
    @Provides
    fun providePopularSeriesNetworkSource(api : MovieApi): PopularSeriesNetworkSource {
        return PopularSeriesNetworkSourceImpl(api = api)
    }

    @Singleton
    @Provides
    fun provideGenreNetworkSource(api : MovieApi): GenreNetworkSource {
        return GenreNetworkSourceImpl(api = api)
    }



}