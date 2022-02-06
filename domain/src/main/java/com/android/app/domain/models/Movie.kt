package com.android.app.domain.models

import java.io.Serializable

data class Movie(
    val title: String?,
    val releaseYear: String?,
    val genre: String?,
    val director: String?,
    val actors: String?,
    val plot: String?,
    val poster: String?,
    val boxOffice: String?,
    var isFavorite:Boolean
):Serializable