package com.android.app.movies.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.app.domain.models.Movie
import com.android.app.movies.R
import com.android.app.movies.databinding.ActivityDetailsBinding
import com.bumptech.glide.Glide


class DetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val Movie = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        intent?.extras?.let {
            val movie: Movie = it.get(Movie) as Movie
            setData(movie)
        }
    }

    private fun setData(movie: Movie) {
        binding.actors.text = getString(R.string.actors) + movie.actors
        binding.directorName.text = getString(R.string.director) + movie.director
        binding.collections.text = getString(R.string.total_collection) + movie.boxOffice
        binding.details.text = movie.plot
        binding.genre.text = getString(R.string.genre) + movie.genre
        binding.releasedYear.text = getString(R.string.release_year) + movie.releaseYear
        Glide.with(binding.root).load(movie.poster).into(binding.fullScreenImage)
        binding.title.text = movie.title
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}