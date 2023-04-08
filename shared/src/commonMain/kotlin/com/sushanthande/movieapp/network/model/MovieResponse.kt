package com.sushanthande.movieapp.network.model

@kotlinx.serialization.Serializable
data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)