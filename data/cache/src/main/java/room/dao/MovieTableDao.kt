package room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import room.entity.MovieTable

@Dao
interface MovieTableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies : MovieTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieTable>)

    @Query("SELECT * FROM MovieTable WHERE movieType = :movieType")
    suspend fun getUpcomingMovies(movieType : String) : List<MovieTable>
}