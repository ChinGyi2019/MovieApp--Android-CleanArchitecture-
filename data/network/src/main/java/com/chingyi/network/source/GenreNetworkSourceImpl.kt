package com.chingyi.network.source

import com.chingyi.common.genre.GenreNetworkSource
import com.chingyi.domain.genre.model.MovieGenre
import com.chingyi.network.api.MovieApi
import com.chingyi.network.response.toGenreList
import com.example.netwrok.helper.executeOrThrow
import javax.inject.Inject

class GenreNetworkSourceImpl @Inject constructor(
    private val api : MovieApi
) : GenreNetworkSource {
    override suspend fun getGenreList() : List<MovieGenre> {
        return executeOrThrow { api.fetchGenreList() }.toGenreList()
    }
}