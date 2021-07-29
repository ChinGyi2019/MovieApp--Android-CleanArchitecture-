package com.chingyi.domain.popular.series

import com.chingyi.domain.CoroutineUseCase
import com.chingyi.domain.DispatcherProvider
import com.chingyi.domain.upcomming.model.Movie
import javax.inject.Inject

class GetPopularSeries @Inject constructor(
    dispatcherProvider : DispatcherProvider,
    private val repository : PopularSeriesRepository
) : CoroutineUseCase<GetPopularSeries.Params, List<Movie>>
    (dispatcherProvider =     dispatcherProvider
){
    data class Params(val page : Int)

    override suspend fun provide(input : Params) : List<Movie> {
        return try {
            repository.getPopularSeries(page = input.page)
        }catch (e :Exception){
            throw  e
        }
    }


}