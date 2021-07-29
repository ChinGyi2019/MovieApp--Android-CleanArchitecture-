package com.chingyi.domain.popular.series

import com.chingyi.domain.upcomming.model.Movie

interface PopularSeriesRepository {
    suspend fun getPopularSeries(page : Int) : List<Movie>
}