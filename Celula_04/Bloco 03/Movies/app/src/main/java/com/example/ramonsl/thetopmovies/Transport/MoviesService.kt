package com.example.ramonsl.thetopmovies.Transport

import com.example.ramonsl.thetopmovies.Data.MoviesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("popular")
    fun retrievePopularMovies(@Query("api_key") apiKey: String?): Call<MoviesDTO?>?

    @GET("top_rated")
    fun retriveTopRatedMovies(@Query("api_key") apiKey: String?): Call<MoviesDTO?>?
}