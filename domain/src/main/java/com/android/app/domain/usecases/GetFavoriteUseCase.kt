package com.android.app.domain.usecases

import com.android.app.domain.Result
import com.android.app.domain.models.Movie
import com.android.app.domain.repositary.DataRepository
import com.android.app.domain.requests.ApiRequest
import javax.inject.Inject

class GetFavoriteUseCase@Inject constructor(private val dataRepo: DataRepository) {
    suspend fun execute(): List<Movie>{
        return dataRepo.getFavorites()
    }
}