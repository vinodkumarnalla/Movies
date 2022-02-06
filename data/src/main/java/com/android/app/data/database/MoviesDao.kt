package com.android.app.data.database

import androidx.room.*
import com.android.app.data.entities.MovieEntity

@Dao
interface MoviesDao {

    companion object {
        const val MOVIE_TABLE = "movie_table"
        const val MOVIE_NAME ="title"
        const val MOVIE_DIRECTOR = "director"
        const val MOVIE_COLLECTIONS ="collections"
        const val MOVIE_ACTORS ="actors"
        const val MOVIE_GENRE = "genre"
        const val MOVIE_DETAILS ="details"
        const val MOVIE_POSTER ="poster"
        const val MOVIE_RELEASE_YEAR ="year"
        const val FAVORITE ="favorite"
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(currency: MovieEntity)

    @Query("DELETE FROM $MOVIE_TABLE WHERE $MOVIE_NAME = :name")
    suspend fun deleteMovie(name: String)

    @Query("SELECT * FROM $MOVIE_TABLE")
    suspend fun getMovies():List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $MOVIE_NAME = :name")
    suspend fun getMovie(name: String):MovieEntity
}