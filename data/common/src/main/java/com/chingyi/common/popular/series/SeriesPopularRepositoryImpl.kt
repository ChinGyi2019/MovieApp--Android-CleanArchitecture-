package com.chingyi.common.popular.series

import com.chingyi.domain.popular.series.PopularSeriesRepository
import com.chingyi.domain.upcomming.model.Movie
import javax.inject.Inject

class SeriesPopularRepositoryImpl @Inject constructor(
    private val networkSource :  PopularSeriesNetworkSource
) : PopularSeriesRepository {
    override suspend fun getPopularSeries(page : Int) : List<Movie> {
      return networkSource.getPopularSeries(page = page)
    }
}