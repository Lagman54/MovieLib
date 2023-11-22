package com.example.finalproject.presentation

import com.example.finalproject.data.model.MovieDetailsEntity
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.data.model.VideoEntity
import com.example.finalproject.presentation.model.Movie
import com.example.finalproject.presentation.model.MovieDetails
import com.example.finalproject.presentation.model.Video

fun MovieEntity.mapToPresentation(): Movie {
    return Movie(this.id, this.title, this.posterPath, this.rating, "")
}

fun MovieDetailsEntity.mapToPresentation(): MovieDetails {
    return MovieDetails(
        id = this.id,
        title = this.title,
        posterUrl = this.posterPath,
        rating = this.rating / 2,
        description = this.description,
        releaseDate = this.releaseDate,
        voteCount = this.voteCount,
        genres = this.genres
    )
}

