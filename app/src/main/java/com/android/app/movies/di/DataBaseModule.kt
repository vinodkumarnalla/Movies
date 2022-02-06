package com.android.app.movies.di

import android.content.Context
import androidx.room.Room
import com.android.app.data.database.AppDatabase
import com.android.app.data.database.MoviesDao
import com.android.app.movies.MovieApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    companion object{
        const val DB_NAME = "movies.db"
    }
    @Provides
    @Singleton
    fun provideRoomDatabase( @ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.movieDao()
    }
}
