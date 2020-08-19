package com.turina1v.moviesfilter.data.network

import com.turina1v.moviesfilter.data.entity.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("/ar2code/apitest/master/movies.json")
    suspend fun getMovies(): Response<List<Movie>>
}
