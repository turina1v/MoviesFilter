package com.turina1v.moviesfilter.ui.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turina1v.moviesfilter.data.entity.Movie
import com.turina1v.moviesfilter.data.network.MovieApiProvider
import com.turina1v.moviesfilter.data.repository.MoviesRepositoryImpl
import com.turina1v.moviesfilter.domain.FilterMoviesUseCase
import com.turina1v.moviesfilter.domain.LoadMoviesListUseCase
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
            _loaderLiveData.value = true
            when (val result = LoadMoviesListUseCase(MoviesRepositoryImpl()).execute()){
                is LoadMoviesListUseCase.LoadMoviesResult.Success -> {
                    _moviesLiveData.value = result.movies
                    initialMoviesList = result.movies
                }
                is LoadMoviesListUseCase.LoadMoviesResult.Error -> _errorLiveData.value = result.errorMessage
            }
            _loaderLiveData.value = false
        }
    }

    fun onYearSwitchCheckedChanged(isChecked: Boolean, yearFilter: Int) {
        if (isChecked) _moviesLiveData.value = FilterMoviesUseCase(yearFilter, initialMoviesList).execute()
        else _moviesLiveData.value = initialMoviesList
    }
}
