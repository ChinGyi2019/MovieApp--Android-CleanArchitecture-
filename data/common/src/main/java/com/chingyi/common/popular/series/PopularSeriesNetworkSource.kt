package com.chingyi.common.popular.series

import com.chingyi.domain.upcomming.model.Movie

interface PopularSeriesNetworkSource {
    suspend fun getPopularSeries(page : Int) : List<Movie>
}