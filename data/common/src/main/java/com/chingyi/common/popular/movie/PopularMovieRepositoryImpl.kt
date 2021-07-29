package com.chingyi.common.popular.movie

import com.chingyi.domain.popular.movie.PopularMovieRepository
import com.chingyi.domain.upcomming.model.Movie
import javax.inject.Inject

class PopularMovieRepositoryImpl @Inject constructor(private val cacheSource : PopularMovieCacheSource,
                                private val networkSource : PopularMovieNetworkSource):PopularMovieRepository {
    override suspend fun getPopularMovies(page : Int) : List<Movie> {
        return networkSource.getPopularMovies(page = page)
    }
}