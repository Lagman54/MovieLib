package com.example.finalproject.domain.model


data class MovieDetails(
    val id: Int,
    val title: String,
    val description: String,
    val posterUrl: String,
    val releaseDate: String,
    val voteCount: Int,
    val genres: List<String>,
    val rating: Float,
)