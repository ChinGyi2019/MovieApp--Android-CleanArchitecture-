package com.chingyi.network.response

import com.chingyi.domain.genre.model.MovieGenre
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreListResponse (
    @Json(name = "genres")
    val genres: List<MovieGenreApiModel>? = null
)

@JsonClass(generateAdapter = true)
data class MovieGenreApiModel (
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "name")
    val name: String? = null
)

fun GenreListResponse.toGenreList() : List<MovieGenre>{
    return genres?.map { it.toMovieGenreModel() } ?: emptyList()
}

fun MovieGenreApiModel.toMovieGenreModel():MovieGenre{

    return MovieGenre(
        id = id,
        name = name
    )

}
