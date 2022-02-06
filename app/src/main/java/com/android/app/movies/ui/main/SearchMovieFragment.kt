package com.android.app.movies.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.app.movies.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieFragment : Fragment(), SearchView.OnQueryTextListener {

    private val movieViewModel by viewModels<MovieViewModel>()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    private fun setupAdapter() {
        movieAdapter = MovieAdapter(emptyList())
        binding.recyclerViewLayout.rvMovies.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewLayout.rvMovies.adapter = movieAdapter

    }

    private fun initObservers() {
        movieViewModel.getResultObserver().observe(viewLifecycleOwner, {
            binding.recyclerViewLayout.rvMovies.visibility = View.VISIBLE
            binding.recyclerViewLayout.txtMessage.visibility = View.GONE
            movieAdapter.setList(it)
        })
        movieViewModel.getErrorObserver().observe(viewLifecycleOwner, {
            binding.recyclerViewLayout.rvMovies.visibility = View.GONE
            binding.recyclerViewLayout.txtMessage.visibility = View.VISIBLE
            binding.recyclerViewLayout.txtMessage.text = it

        })
        movieViewModel.getProgressObserver().observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root
        setupAdapter()
        initObservers()
        binding.searchView.setOnQueryTextListener(this)
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query?.isNotEmpty() == true) {
            movieViewModel.searchMovies(query.trim())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}