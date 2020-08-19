package com.turina1v.moviesfilter.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiProvider {
    val api: MovieApi = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)
}
