package com.example.finalproject.data.mapper

import com.example.finalproject.common.Const.Api.IMAGE_URL
import com.example.finalproject.common.Const.Api.YOUTUBE_URL
import com.example.finalproject.common.Const.Api.genres
import com.example.finalproject.data.model.GenreEntity
import com.example.finalproject.data.model.MovieDetailsEntity
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.data.model.VideoEntity
import com.example.finalproject.domain.model.Genre
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Video

fun MovieEntity.mapToDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        posterUrl = IMAGE_URL + this.posterPath,
        rating = this.rating,
        genre = getGenres(genreIds)
    )
}

fun MovieDetailsEntity.mapToDomain(): MovieDetails {
    return MovieDetails(
        id = this.id,
        title = this.title,
        posterUrl = IMAGE_URL + this.posterPath,
        rating = this.rating / 2,
        description = this.description,
        releaseDate = this.releaseDate,
        voteCount = this.voteCount,
        genres = this.genres.map(GenreEntity::mapToDomain)
    )
}

fun GenreEntity.mapToDomain(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun VideoEntity.mapToDomain(): Video {
    return Video(
        type = this.type,
        name = this.name,
        url = YOUTUBE_URL + this.key
    )
}

private fun getGenres(ids: List<Int>): String {
    return when(ids.size) {
        0 -> ""
        1 -> genres[ids[0]] + ""
        else -> genres[ids[0]] + ", " + genres[ids[1]]
    }
}