package com.turina1v.moviesfilter.domain.repository

import com.turina1v.moviesfilter.data.entity.Movie

interface MoviesRepository {
    suspend fun loadMovies(): List<Movie>
}
