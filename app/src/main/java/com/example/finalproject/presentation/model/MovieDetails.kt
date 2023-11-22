package com.example.finalproject.presentation.model

import com.example.finalproject.data.model.Genre


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