package com.example.finalproject.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("vote_average")
    val rating: Float
)