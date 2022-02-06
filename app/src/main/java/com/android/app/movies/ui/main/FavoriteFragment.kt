package com.android.app.movies.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.app.movies.R
import com.android.app.movies.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val movieViewModel by viewModels<MovieViewModel>()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        setupAdapter()
        initObservers()
        return binding.root
    }

    private fun setupAdapter() {
        movieAdapter = MovieAdapter(emptyList())
        binding.recyclerViewLayout.rvMovies.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewLayout.rvMovies.adapter = movieAdapter
    }

    private fun initObservers() {
        movieViewModel.getFavoritesObserver().observe(viewLifecycleOwner, {
            binding.recyclerViewLayout.rvMovies.visibility = View.VISIBLE
            binding.recyclerViewLayout.txtMessage.visibility = View.GONE
            movieAdapter.setList(it)
        })
        movieViewModel.getNoFavoritesObserver().observe(viewLifecycleOwner,{
            binding.recyclerViewLayout.txtMessage.visibility= View.VISIBLE
            binding.recyclerViewLayout.rvMovies.visibility= View.GONE
            binding.recyclerViewLayout.txtMessage.text = getString(R.string.no_favorites)
        })
        movieAdapter.getOnClickEventLiveData().observe(viewLifecycleOwner, {
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.Movie, it)
            startActivity(intent)
        })
        movieAdapter.getOnFavClickEventLiveData().observe(viewLifecycleOwner, {
            movieViewModel.toggleFavorite(it)

        })
    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser) {
            movieViewModel.getFavorites()
        }
    }

}