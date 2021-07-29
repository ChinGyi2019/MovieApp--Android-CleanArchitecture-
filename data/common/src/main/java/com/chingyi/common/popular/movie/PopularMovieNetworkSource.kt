package com.chingyi.common.popular.movie

import com.chingyi.domain.upcomming.model.Movie

interface PopularMovieNetworkSource {
    suspend fun getPopularMovies(page : Int) : List<Movie>
}