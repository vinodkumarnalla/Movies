package com.android.app.data.mappers

import com.android.app.data.entities.MovieEntity
import com.android.app.domain.models.Movie
import javax.inject.Inject

class FavoritesMovieMapper @Inject constructor() : BaseMapper<List<MovieEntity>, List<Movie>> {


    override suspend fun transform(list: List<MovieEntity>): List<Movie> {
        val result = ArrayList<Movie>()
        for (data in list) {
            val movie = Movie(
                title = data.movieName,
                actors = data.actors,
                boxOffice = data.collections,
                plot = data.details,
                director = data.director,
                isFavorite = data.favorite,
                genre = data.genre,
                poster = data.poster,
                releaseYear = data.year
            )
            result.add(movie)
        }
        return result
    }
}