package com.chingyi.domain.popular.movie.usecase

import com.chingyi.domain.CoroutineUseCase
import com.chingyi.domain.DispatcherProvider
import com.chingyi.domain.popular.movie.PopularMovieRepository
import com.chingyi.domain.upcomming.model.Movie
import java.io.IOException
import javax.inject.Inject


class GetPopularMovies @Inject constructor(private val repository : PopularMovieRepository,
                                            dispatcherProvider : DispatcherProvider
):CoroutineUseCase<GetPopularMovies.Params, List<Movie>>(dispatcherProvider = dispatcherProvider) {

    data class Params(val page : Int)

    override suspend fun provide(input : Params) : List<Movie> {
        return try {
            repository.getPopularMovies(page = input.page)
        } catch (e: IOException) {
            throw e
        }
    }
}