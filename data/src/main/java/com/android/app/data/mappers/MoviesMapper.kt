package com.android.app.data.mappers

import com.android.app.data.database.MoviesDao
import com.android.app.data.response.MovieResponse
import com.android.app.domain.models.Movie
import javax.inject.Inject


class MoviesMapper @Inject constructor(private val dao: MoviesDao) :
    BaseMapper<MovieResponse, List<Movie>> {


    override suspend fun transform(data: MovieResponse): List<Movie> {
        val list = arrayListOf<Movie>()
        var isFavorite = false
        data.title?.let {
            val movie = dao.getMovie(it)
            movie?.let {
                isFavorite = it.favorite
            }

        }

        val movie = Movie(
            data.title,
            data.year,
            data.genre,
            data.director,
            data.actors,
            data.plot,
            data.poster,
            data.boxOffice,
            isFavorite
        )
        list.add(movie)
        return list;
    }
}