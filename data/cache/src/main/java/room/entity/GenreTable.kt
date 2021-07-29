package room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreTable(
    @PrimaryKey
    var id : Long ? = null,
    var name : String? = null
)
