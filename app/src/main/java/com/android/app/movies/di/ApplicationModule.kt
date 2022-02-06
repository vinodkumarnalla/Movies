package com.android.app.movies.di

import com.android.app.data.APIInterface
import com.android.app.data.DataRepositoryImplementation
import com.android.app.data.database.MoviesDao
import com.android.app.data.mappers.FavoritesMovieMapper
import com.android.app.data.mappers.MoviesDbMapper
import com.android.app.data.mappers.MoviesMapper
import com.android.app.domain.repositary.DataRepository
import com.android.app.domain.usecases.SearchMoviesUseCase
import com.android.app.domain.usecases.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDataRepository(apiInterface: APIInterface,moviesMapper: MoviesMapper,moviesDao: MoviesDao,moviesDbMapper: MoviesDbMapper,favoritesMovieMapper: FavoritesMovieMapper): DataRepository =
         DataRepositoryImplementation(apiInterface,moviesMapper,moviesDao,moviesDbMapper,favoritesMovieMapper);

    @Provides
    @Singleton
    fun provideSearchMovieUseCase(dataRepository: DataRepository): SearchMoviesUseCase =
        SearchMoviesUseCase(dataRepository);

    @Provides
    @Singleton
    fun provideToggleFavoriteMovieUseCase(dataRepository: DataRepository): ToggleFavoriteUseCase =
        ToggleFavoriteUseCase(dataRepository);
}