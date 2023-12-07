package com.example.finalproject.domain.model

import androidx.annotation.DrawableRes
import com.example.finalproject.R

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val rating: Float?,
    val genre: String,
)