package com.example.finalproject.domain.model

import com.example.finalproject.data.model.GenreEntity


data class MovieDetails(
    val id: Int,
    val title: String,
    val description: String,
    val posterUrl: String,
    val releaseDate: String,
    val voteCount: Int,
    val genres: List<Genre>,
    val rating: Float,
)