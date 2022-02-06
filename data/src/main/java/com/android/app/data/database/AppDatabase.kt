package com.android.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.app.data.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MoviesDao

}