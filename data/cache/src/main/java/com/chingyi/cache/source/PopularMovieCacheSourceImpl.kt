package com.chingyi.cache.source

import com.chingyi.common.popular.movie.PopularMovieCacheSource
import com.chingyi.domain.upcomming.model.Movie
import room.db.AppDatabase
import javax.inject.Inject

class PopularMovieCacheSourceImpl @Inject constructor(
    private val db : AppDatabase
) : PopularMovieCacheSource {
    override suspend fun getPopularMovies(page : Int) : List<Movie> {
        return emptyList()
    }
}