package com.chingyi.common.upcoming

import com.chingyi.domain.upcomming.model.Movie

interface UpcomingMovieCacheSource {

    suspend fun getUpcomingMovieList(movieType: String) : List<Movie>

}