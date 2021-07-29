package com.chingyi.network.source

import com.chingyi.common.upcoming.UpComingMovieNetworkSource
import com.chingyi.domain.upcomming.model.Movie
import com.chingyi.network.api.MovieApi
import com.chingyi.network.response.toMovieList
import com.example.netwrok.helper.executeOrThrow
import timber.log.Timber
import timber.log.Timber.d
import javax.inject.Inject

class UpComingMovieNetworkSourceImpl @Inject constructor(
    private val api : MovieApi
): UpComingMovieNetworkSource {

    override suspend fun getUpComingMovieList() : List<Movie> {
        return executeOrThrow { api.fetchUpComingMovies() }.toMovieList()
    }
}