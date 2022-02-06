package com.android.app.movies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.domain.Result
import com.android.app.domain.models.Movie
import com.android.app.domain.requests.ApiRequest
import com.android.app.domain.usecases.GetFavoriteUseCase
import com.android.app.domain.usecases.SearchMoviesUseCase
import com.android.app.domain.usecases.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase
) :
    ViewModel() {
    private var resultLiveData = MutableLiveData<List<Movie>>()
    private var errorLiveData = MutableLiveData<String>()
    private var progressLiveData = MutableLiveData<Boolean>()
    private var noFavoritesLiveData = MutableLiveData<Boolean>()
    private var favoritesLiveData = MutableLiveData<List<Movie>>()

    fun searchMovies(searchItem: String) {
        viewModelScope.async {
            progressLiveData.postValue(true)
            val request = ApiRequest()
            request.searchKey = searchItem
            try {
                when (val result = searchMoviesUseCase.execute(request)) {
                    is Result.Success -> {
                        progressLiveData.postValue(false)
                        result.value?.let {
                            resultLiveData.postValue(it)
                        }
                    }
                    is Result.Failure -> {
                        progressLiveData.postValue(false)
                        errorLiveData.postValue(result.message.message)
                    }
                }
            } catch (excp: Exception) {
                progressLiveData.postValue(false)
                errorLiveData.postValue(excp.message)
            }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            val result = toggleFavoriteUseCase.execute(movie)
            getFavorites()
        }
    }

    fun getResultObserver(): LiveData<List<Movie>> {
        return resultLiveData;
    }

    fun getFavoritesObserver(): LiveData<List<Movie>> {
        return favoritesLiveData;
    }

    fun getErrorObserver(): LiveData<String> {
        return errorLiveData;
    }

    fun getProgressObserver(): LiveData<Boolean> {
        return progressLiveData;
    }

    fun getFavorites() {
        viewModelScope.async {
            val movies = getFavoriteUseCase.execute()
            if (movies.isNotEmpty()) {
                favoritesLiveData.postValue(movies)
            } else {
                noFavoritesLiveData.postValue(true)
            }
        }
    }

    fun getNoFavoritesObserver(): LiveData<Boolean> {
        return noFavoritesLiveData;
    }
}