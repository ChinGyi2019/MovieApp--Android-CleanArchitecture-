package com.chingyi.domain.popular.movie

import com.chingyi.domain.upcomming.model.Movie

interface PopularMovieRepository {
    suspend fun  getPopularMovies(page : Int = 1) : List<Movie>
}