package com.chingyi.moviedbappcleanarchitecture.di.module

import android.app.Application
import android.content.Context
import com.chingyi.domain.DispatcherProvider
import com.chingyi.domain.genre.GenreRepository
import com.chingyi.domain.genre.usecase.GetGenreList
import com.chingyi.domain.popular.movie.PopularMovieRepository
import com.chingyi.domain.popular.movie.usecase.GetPopularMovies
import com.chingyi.domain.popular.series.GetPopularSeries
import com.chingyi.domain.popular.series.PopularSeriesRepository
import com.chingyi.domain.upcomming.UpComingMovieRepository
import com.chingyi.domain.upcomming.usecase.GetUpComingMovieList
import com.chingyi.moviedbappcleanarchitecture.helper.AndroidDispatcherProvider
import com.chingyi.moviedbappcleanarchitecture.helper.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun context(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun dispatcherProvider() : DispatcherProvider {
        return AndroidDispatcherProvider()
    }




    @Singleton
    @Provides
    fun upComingMovieUseCase(dispatcherProvider: DispatcherProvider,
                                upComingMovieRepository: UpComingMovieRepository): GetUpComingMovieList {
        return GetUpComingMovieList(dispatcherProvider = dispatcherProvider,
            repositoryUpComing = upComingMovieRepository)
    }

    @Singleton
    @Provides
    fun popularMovieUseCase(dispatcherProvider: DispatcherProvider,
                             repository: PopularMovieRepository): GetPopularMovies {
        return GetPopularMovies(dispatcherProvider = dispatcherProvider,
            repository = repository)
    }

    @Singleton
    @Provides
    fun popularSeriesUseCase(dispatcherProvider: DispatcherProvider,
                            repository: PopularSeriesRepository): GetPopularSeries {
        return GetPopularSeries(dispatcherProvider = dispatcherProvider,
            repository = repository)
    }

    @Singleton
    @Provides
    fun genreListUseCase(dispatcherProvider: DispatcherProvider,
                             repository: GenreRepository): GetGenreList {
        return GetGenreList(dispatcherProvider = dispatcherProvider,
            repository = repository)
    }


}

//@InstallIn(ActivityComponent::class)
//@Module
//class ActivityModule{
//
//    @Provides
//    fun provideAppExecutors() : AppExecutors {
//        return AppExecutors()
//    }
//}
