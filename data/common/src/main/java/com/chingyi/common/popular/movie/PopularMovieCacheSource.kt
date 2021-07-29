package com.chingyi.common.popular.movie

import com.chingyi.domain.upcomming.model.Movie

interface PopularMovieCacheSource {
    suspend fun getPopularMovies(page : Int ) : List<Movie>
}