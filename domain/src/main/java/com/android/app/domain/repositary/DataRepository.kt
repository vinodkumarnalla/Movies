package com.android.app.domain.repositary

import com.android.app.domain.models.Movie
import com.android.app.domain.requests.ApiRequest
import com.android.app.domain.Result


interface DataRepository {
    suspend fun searchMovie(request: ApiRequest): Result<List<Movie>>
    suspend fun toggleFavorite(movie: Movie): Result<Boolean>
    suspend fun getFavorites(): List<Movie>

}