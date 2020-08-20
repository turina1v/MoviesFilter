package com.turina1v.moviesfilter.domain

import com.turina1v.moviesfilter.data.entity.Movie

class FilterMoviesUseCase(private val year: Int, private val movies: List<Movie>) {
    fun execute(): List<Movie>{
        var filteredMovies: MutableList<Movie> = mutableListOf()
        movies.forEach {
            if (it.year == year) filteredMovies.add(it)
        }
        return filteredMovies
    }
}
