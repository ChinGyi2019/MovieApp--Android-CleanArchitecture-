package com.chingyi.cache.source

import com.chingyi.cache.mapper.toMovieModel
import com.chingyi.common.upcoming.UpcomingMovieCacheSource
import com.chingyi.domain.upcomming.model.Movie
import room.db.AppDatabase
import javax.inject.Inject

class UpComingMovieCacheSourceImpl @Inject constructor(
    private val db : AppDatabase
) : UpcomingMovieCacheSource {
    override suspend fun getUpcomingMovieList(movieType : String) : List<Movie> {
     return  db.movieTableDao().getUpcomingMovies(movieType = movieType)
         .map { it.toMovieModel() }
    }
}