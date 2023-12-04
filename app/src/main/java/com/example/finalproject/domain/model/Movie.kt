package com.example.finalproject.domain.model

import androidx.annotation.DrawableRes
import com.example.finalproject.R

data class Movie(
    val id: Int = 0,
    val title: String = "The Batman",
    val posterUrl: String ="",
    val rating: Float? = 5.0f,
    val genre: String = "Action, Comedy",
    // TODO delete this in the future
    @DrawableRes
    val posterRes: Int = R.drawable.poster
)