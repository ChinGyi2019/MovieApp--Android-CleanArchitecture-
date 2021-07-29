package com.chingyi.common.genre

import com.chingyi.domain.genre.model.MovieGenre

interface GenreNetworkSource {
    suspend fun getGenreList():List<MovieGenre>
}