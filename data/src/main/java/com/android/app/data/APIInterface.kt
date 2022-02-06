package com.android.app.data

import com.android.app.data.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {


    @GET(".")
    suspend fun searchMovie(
        @Query("apikey") apiKey: String,@Query("t") search: String
    ): Response<MovieResponse>
}