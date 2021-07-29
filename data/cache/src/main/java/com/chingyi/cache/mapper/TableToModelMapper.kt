package com.chingyi.cache.mapper

import com.chingyi.domain.upcomming.model.Movie
import room.dao.MovieTableDao
import room.entity.MovieTable

fun MovieTable.toMovieModel():Movie{
    return Movie(
        id = id ,
        adult = adult ,
        backdropPath = backdropPath ,
        originalLanguage = originalLanguage ,
        originalTitle = originalTitle ,
        overview = overview ,
        popularity = popularity ,
        posterPath = posterPath ,
        releaseDate = releaseDate ,
        title = title ,
        video = video ,
        voteAverage = voteAverage ,
        voteCount = voteCount

    )
}