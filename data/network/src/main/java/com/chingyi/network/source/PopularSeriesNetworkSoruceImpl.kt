package com.chingyi.network.source

import com.chingyi.common.popular.series.PopularSeriesNetworkSource
import com.chingyi.domain.upcomming.model.Movie
import com.chingyi.network.api.MovieApi
import com.chingyi.network.response.toMovieList
import com.example.netwrok.helper.executeOrThrow

class PopularSeriesNetworkSourceImpl(private val api : MovieApi) : PopularSeriesNetworkSource {
    override suspend fun getPopularSeries(page : Int) : List<Movie> {
        return executeOrThrow { api.fetchPopularSeries(page =page) }.toMovieList()
    }
}