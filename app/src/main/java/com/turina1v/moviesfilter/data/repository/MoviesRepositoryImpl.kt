package com.turina1v.moviesfilter.data.repository

import com.turina1v.moviesfilter.data.entity.Movie
import com.turina1v.moviesfilter.data.network.MovieApiProvider
import com.turina1v.moviesfilter.domain.repository.MoviesRepository

class MoviesRepositoryImpl(): MoviesRepository {
    override suspend fun loadMovies(): List<Movie> = MovieApiProvider.api.getMovies()
}
