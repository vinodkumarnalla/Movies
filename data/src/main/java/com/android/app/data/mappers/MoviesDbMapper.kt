package com.android.app.data.mappers

import com.android.app.data.entities.MovieEntity
import com.android.app.domain.models.Movie
import javax.inject.Inject

class MoviesDbMapper @Inject constructor() : BaseMapper<Movie, MovieEntity> {


    override suspend fun transform(data: Movie): MovieEntity {

        return MovieEntity(
            movieName = data.title ?: "",
            actors = data.actors ?: "",
            collections = data.boxOffice ?: "",
            details = data.plot ?: "",
            director = data.director ?: "",
            favorite = data.isFavorite,
            genre = data.genre ?: "",
            poster = data.poster ?: "",
            year = data.releaseYear ?: ""
        );
    }
}