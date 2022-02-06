package com.android.app.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.app.data.database.MoviesDao

@Entity(tableName = MoviesDao.MOVIE_TABLE)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = MoviesDao.MOVIE_NAME) val movieName: String,
    @ColumnInfo(name = MoviesDao.MOVIE_DIRECTOR) val director: String,
    @ColumnInfo(name = MoviesDao.MOVIE_ACTORS) val actors: String,
    @ColumnInfo(name = MoviesDao.MOVIE_COLLECTIONS) val collections: String,
    @ColumnInfo(name = MoviesDao.MOVIE_DETAILS) val details: String,
    @ColumnInfo(name = MoviesDao.MOVIE_GENRE) val genre: String,
    @ColumnInfo(name = MoviesDao.MOVIE_POSTER) val poster: String,
    @ColumnInfo(name = MoviesDao.MOVIE_RELEASE_YEAR) val year: String,
    @ColumnInfo(name = MoviesDao.FAVORITE) val favorite: Boolean,

    )