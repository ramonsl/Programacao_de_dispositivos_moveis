package com.example.ramonsl.thetopmovies.Data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class MoviesDTO {
    @JsonProperty("results")
    var movies: List<Movie> = ArrayList()

}