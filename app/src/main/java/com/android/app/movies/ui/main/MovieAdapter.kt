package com.android.app.movies.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.android.app.domain.models.Movie
import com.android.app.movies.R
import com.android.app.movies.databinding.SearchMovieItemBinding
import com.bumptech.glide.Glide

class MovieAdapter(private var list: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val onClickLiveData = MutableLiveData<Movie>()
    private val onFavClickLiveData = MutableLiveData<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SearchMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel: Movie = list[position]
        holder.bind(dataModel)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private var binding: SearchMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.title.text = movie.title
            binding.directorName.text =
                binding.root.resources.getString(R.string.director) + movie.director
            binding.info.text = movie.plot
            binding.releaseYear.text =
                binding.root.resources.getString(R.string.release_year) + movie.releaseYear
            Glide.with(binding.root).load(movie.poster).into(binding.thumbnail)
            if (movie.isFavorite) {
                binding.favorite.setImageResource(R.drawable.fav_heart)
            } else {
                binding.favorite.setImageResource(R.drawable.fav_icon)
            }

            binding.cardView.setOnClickListener {
                onClickLiveData.postValue(movie)
            }
            binding.favorite.setOnClickListener {
                movie.isFavorite = !movie.isFavorite
                notifyItemChanged(absoluteAdapterPosition)
                onFavClickLiveData.postValue(movie)
            }
        }
    }

    fun setList(list: List<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun getOnClickEventLiveData(): LiveData<Movie> {
        return onClickLiveData
    }

    fun getOnFavClickEventLiveData(): LiveData<Movie> {
        return onFavClickLiveData
    }
}