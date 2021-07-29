package com.chingyi.moviedbappcleanarchitecture.di.module

import com.chingyi.common.genre.GenreNetworkSource
import com.chingyi.common.genre.GenreRepositoryImpl
import com.chingyi.common.popular.movie.PopularMovieCacheSource
import com.chingyi.common.popular.movie.PopularMovieNetworkSource
import com.chingyi.common.popular.movie.PopularMovieRepositoryImpl
import com.chingyi.common.popular.series.PopularSeriesNetworkSource
import com.chingyi.common.popular.series.SeriesPopularRepositoryImpl
import com.chingyi.common.upcoming.UpComingMovieNetworkSource
import com.chingyi.common.upcoming.UpComingMovieRepositoryImpl
import com.chingyi.common.upcoming.UpcomingMovieCacheSource
import com.chingyi.domain.genre.GenreRepository
import com.chingyi.domain.popular.movie.PopularMovieRepository
import com.chingyi.domain.popular.series.PopularSeriesRepository
import com.chingyi.domain.upcomming.UpComingMovieRepository
import com.chingyi.network.source.PopularSeriesNetworkSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule{

    @Singleton
    @Provides
    fun provideUpComingRepository(upComingCacheSource: UpcomingMovieCacheSource,
                                  upComingNetworkSource: UpComingMovieNetworkSource):UpComingMovieRepository {
        return UpComingMovieRepositoryImpl(
            upcomingCacheSource = upComingCacheSource,
            upcomingNetworkSource = upComingNetworkSource
        )
    }

    @Singleton
    @Provides
    fun providePopularRepository(cacheSource: PopularMovieCacheSource,
                                  networkSource : PopularMovieNetworkSource):PopularMovieRepository {
        return PopularMovieRepositoryImpl(
            cacheSource = cacheSource,
            networkSource = networkSource
        )
    }

    @Singleton
    @Provides
    fun providePopularSeriesRepository(networkSource : PopularSeriesNetworkSource):PopularSeriesRepository {
        return SeriesPopularRepositoryImpl(
            networkSource = networkSource
        )
    }

    @Singleton
    @Provides
    fun provideGenreRepository(networkSource : GenreNetworkSource):GenreRepository {
        return GenreRepositoryImpl(
            networkSource = networkSource
        )
    }

}