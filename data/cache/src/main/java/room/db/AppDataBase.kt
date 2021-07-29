package room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import room.dao.MovieTableDao
import room.entity.GenreTable
import room.entity.MovieTable


@Database(entities = [MovieTable::class, GenreTable::class],
    version = 2,
    exportSchema= false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieTableDao():MovieTableDao

    companion object {
        const val DB_NAME = "movie_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}