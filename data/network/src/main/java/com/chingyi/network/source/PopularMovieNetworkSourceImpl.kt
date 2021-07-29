package com.chingyi.network.source

import com.chingyi.common.popular.movie.PopularMovieNetworkSource
import com.chingyi.domain.upcomming.model.Movie
import com.chingyi.network.api.MovieApi
import com.chingyi.network.response.toMovieList
import com.example.netwrok.helper.executeOrThrow
import javax.inject.Inject

class PopularMovieNetworkSourceImpl @Inject constructor(
    private val api : MovieApi
) : PopularMovieNetworkSource {
    override suspend fun getPopularMovies(page : Int) : List<Movie> {
        return executeOrThrow { api.fetchPopularMovies(page = page) }.toMovieList()
    }
}