package com.turina1v.moviesfilter.ui.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turina1v.moviesfilter.data.entity.Movie
import com.turina1v.moviesfilter.data.network.MovieApiProvider
import com.turina1v.moviesfilter.domain.FilterMoviesUseCase
import kotlinx.coroutines.launch

class PosterViewModel : ViewModel() {
    private val _moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesLiveData: LiveData<List<Movie>>
        get() = _moviesLiveData

    private val _loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loaderLiveData: LiveData<Boolean>
        get() = _loaderLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    private lateinit var initialMoviesList: List<Movie>

    init {
        viewModelScope.launch {
            try {
                _loaderLiveData.value = true
                val response = MovieApiProvider.api.getMovies()
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _moviesLiveData.value = response.body()
                        initialMoviesList = response.body()!!
                    } else {
                        _errorLiveData.value = "No movies found"
                    }
                } else {
                    _errorLiveData.value = "Error, code = ${response.code()}"
                }
            } catch (e: Throwable) {
                _errorLiveData.value = "Error: ${e.message}"
            } finally {
                _loaderLiveData.value = false
            }
        }
    }

    fun onYearSwitchCheckedChanged(isChecked: Boolean, yearFilter: Int) {
        if (isChecked) _moviesLiveData.value = FilterMoviesUseCase(yearFilter, initialMoviesList).execute()
        else _moviesLiveData.value = initialMoviesList
    }
}
