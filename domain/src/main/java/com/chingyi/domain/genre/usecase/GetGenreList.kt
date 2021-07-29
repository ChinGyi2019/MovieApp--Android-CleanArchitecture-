package com.chingyi.domain.genre.usecase

import com.chingyi.domain.CoroutineUseCase
import com.chingyi.domain.DispatcherProvider
import com.chingyi.domain.genre.GenreRepository
import com.chingyi.domain.genre.model.MovieGenre
import javax.inject.Inject

class GetGenreList  @Inject constructor(
    private val repository : GenreRepository,
    dispatcherProvider : DispatcherProvider
) : CoroutineUseCase<GetGenreList.Params, List<MovieGenre>>(dispatcherProvider = dispatcherProvider) {
    data class Params(val value : Int)

    override suspend fun provide(input : Params) : List<MovieGenre> {
        return  try {
          repository.getGenreList()
        }catch (e : Exception){
            throw  e
        }
    }
}