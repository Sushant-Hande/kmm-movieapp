package com.sushanthande.movieapp.network.model

import com.sushanthande.movieapp.network.AppConstant.POSTER_BASE_URL

@kotlinx.serialization.Serializable
data class Result(
    val adult: Boolean,
    val backdrop_path: String  = "",
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){
    fun getImagePath() = POSTER_BASE_URL + poster_path
}