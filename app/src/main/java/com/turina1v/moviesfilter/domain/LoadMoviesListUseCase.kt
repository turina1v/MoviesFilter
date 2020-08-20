package com.turina1v.moviesfilter.domain

import com.turina1v.moviesfilter.data.entity.Movie
import com.turina1v.moviesfilter.domain.repository.MoviesRepository

class LoadMoviesListUseCase(private val moviesRepository: MoviesRepository) {

    sealed class LoadMoviesResult {
        data class Success(val movies: List<Movie>): LoadMoviesResult()
        data class Error(val errorMessage: String): LoadMoviesResult()
    }

    suspend fun execute(): LoadMoviesResult{
        return try {
            LoadMoviesResult.Success(moviesRepository.loadMovies())
        } catch (e: Throwable){
            LoadMoviesResult.Error(e.message ?: "Error loading movies")
        }
    }
}

