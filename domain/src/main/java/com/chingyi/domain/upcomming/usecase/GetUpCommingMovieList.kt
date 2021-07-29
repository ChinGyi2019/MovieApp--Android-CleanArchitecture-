package com.chingyi.domain.upcomming.usecase

import com.chingyi.domain.CoroutineUseCase
import com.chingyi.domain.DispatcherProvider
import com.chingyi.domain.upcomming.UpComingMovieRepository
import com.chingyi.domain.upcomming.model.Movie
import java.io.IOException
import javax.inject.Inject

class GetUpComingMovieList @Inject constructor(
    dispatcherProvider : DispatcherProvider,
    private  val repositoryUpComing : UpComingMovieRepository
) : CoroutineUseCase<GetUpComingMovieList.Params ,List<Movie>>(dispatcherProvider = dispatcherProvider){

    data class Params(
        val param: String
    )

    override suspend fun provide(input : Params) :List<Movie> {
        return try {
            repositoryUpComing.getUpComingMovieList()
        } catch (e: IOException) {
            throw e
        }
    }
}