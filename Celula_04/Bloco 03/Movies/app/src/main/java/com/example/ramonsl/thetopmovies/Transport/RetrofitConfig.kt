package com.example.ramonsl.thetopmovies.Transport

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitConfig {
    private val retrofit: Retrofit
    val BASE_URL = "https://api.themoviedb.org/3/movie/"
     val BASE_IMG = "https://image.tmdb.org/t/p/w500/"
    fun get(): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
    }
}