package com.chingyi.common.upcoming

import com.chingyi.domain.upcomming.UpComingMovieRepository
import com.chingyi.domain.upcomming.model.Movie
import javax.inject.Inject

class UpComingMovieRepositoryImpl @Inject constructor(
    private val upcomingCacheSource : UpcomingMovieCacheSource,
    private val upcomingNetworkSource : UpComingMovieNetworkSource
) : UpComingMovieRepository {
    override suspend fun getUpComingMovieList() : List<Movie> {
        return  upcomingNetworkSource.getUpComingMovieList()
    }
}