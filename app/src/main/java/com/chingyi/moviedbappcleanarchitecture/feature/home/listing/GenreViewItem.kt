package com.chingyi.moviedbappcleanarchitecture.feature.home.listing

import com.chingyi.domain.genre.model.MovieGenre
import com.chingyi.domain.upcomming.model.Movie

data class GenreViewItem(
    var id : Long? = null,

    var name : String? = null,

    var isSelected : Boolean? = false

)

fun MovieGenre.toGenreViewItem() : GenreViewItem{
    return GenreViewItem(
        id = id,
        name = name
    )
}