package com.chingyi.domain.genre

import com.chingyi.domain.genre.model.MovieGenre

interface GenreRepository {

    suspend fun getGenreList(): List<MovieGenre>
}