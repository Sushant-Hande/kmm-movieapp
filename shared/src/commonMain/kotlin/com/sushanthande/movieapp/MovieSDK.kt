package com.sushanthande.movieapp

import com.sushanthande.movieapp.network.MovieApi
import com.sushanthande.movieapp.network.model.MovieResponse

/**
 * Created by Sushant Hande on 25/03/23.
 */
class MovieSDK {
    private val movieApi = MovieApi()

    @Throws(Exception::class)
    suspend fun getPopularMovies(): MovieResponse {
        val popularMovieResponse = movieApi.getPopularMovies()
        return if (popularMovieResponse != null) {
            popularMovieResponse
        } else {
            throw NullPointerException()
        }
    }
}