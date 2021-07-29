package com.chingyi.common.upcoming

import com.chingyi.domain.upcomming.model.Movie

interface UpComingMovieNetworkSource {

    suspend fun getUpComingMovieList() : List<Movie>
}