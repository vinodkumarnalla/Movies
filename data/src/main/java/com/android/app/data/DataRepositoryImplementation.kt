package com.android.app.data

import com.android.app.data.database.MoviesDao
import com.android.app.data.mappers.FavoritesMovieMapper
import com.android.app.data.mappers.MoviesDbMapper
import com.android.app.data.mappers.MoviesMapper
import com.android.app.domain.Result
import com.android.app.domain.models.Movie
import com.android.app.domain.repositary.DataRepository
import com.android.app.domain.requests.ApiRequest
import javax.inject.Inject

class DataRepositoryImplementation @Inject constructor(
    private val api: APIInterface,
    private val moviesMapper: MoviesMapper,
    private val moviesDao: MoviesDao,
    private val moviesDbMapper: MoviesDbMapper,
    private val favoritesMovieMapper: FavoritesMovieMapper
) :
    DataRepository {
    override suspend fun searchMovie(request: ApiRequest): Result<List<Movie>> {
        val data = request.searchKey?.let { api.searchMovie(APIConstants.API_KEY, it) }
        return if (data?.isSuccessful == true && data.body() != null && data.body()?.title?.isNotEmpty() == true) {
            Result.Success(moviesMapper.transform(data = data.body()!!))
        } else {
            Result.Failure(Throwable(APIConstants.ERROR_MESSAGE));
        }
    }

    override suspend fun toggleFavorite(movie: Movie): Result<Boolean> {
        if (movie.isFavorite) {
            val movieEntity = moviesDbMapper.transform(movie)
            moviesDao.addMovie(movieEntity)
        } else {
            movie?.title?.let {
                moviesDao.deleteMovie(it)
            }

        }
        return Result.Success(true)
    }

    override suspend fun getFavorites(): List<Movie> {
        val data = moviesDao.getMovies()
        return favoritesMovieMapper.transform(data)
    }

}