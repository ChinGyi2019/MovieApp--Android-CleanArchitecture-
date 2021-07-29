package com.chingyi.common.genre

import com.chingyi.domain.genre.GenreRepository
import com.chingyi.domain.genre.model.MovieGenre
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val networkSource : GenreNetworkSource
) : GenreRepository{
    override suspend fun getGenreList() : List<MovieGenre> {
        return networkSource.getGenreList()
    }

}