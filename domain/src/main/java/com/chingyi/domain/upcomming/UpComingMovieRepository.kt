package com.chingyi.domain.upcomming

import com.chingyi.domain.upcomming.model.Movie

interface UpComingMovieRepository {
    suspend fun getUpComingMovieList(): List<Movie>
}