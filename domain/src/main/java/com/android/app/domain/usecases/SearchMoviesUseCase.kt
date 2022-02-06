package com.android.app.domain.usecases

import com.android.app.domain.Result
import com.android.app.domain.models.Movie
import com.android.app.domain.repositary.DataRepository
import com.android.app.domain.requests.ApiRequest
import javax.inject.Inject

class SearchMoviesUseCase@Inject constructor(private val dataRepo: DataRepository) {
    suspend fun execute(request: ApiRequest): Result<List<Movie>>{
        return dataRepo.searchMovie(request)
    }
}