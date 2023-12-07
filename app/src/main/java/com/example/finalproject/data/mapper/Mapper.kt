package com.example.finalproject.data.mapper

import com.example.finalproject.common.Const.Api.IMAGE_URL
import com.example.finalproject.common.Const.Api.YOUTUBE_URL
import com.example.finalproject.common.Const.Api.genres
import com.example.finalproject.common.round
import com.example.finalproject.data.db.WatchListMovieEntity
import com.example.finalproject.data.network.model.GenreEntity
import com.example.finalproject.data.network.model.MovieDetailsEntity
import com.example.finalproject.data.network.model.MovieEntity
import com.example.finalproject.data.network.model.VideoEntity
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Trailer
import com.example.finalproject.domain.model.Video

fun MovieEntity.mapToDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        posterUrl = IMAGE_URL + this.posterPath,
        rating = (this.rating / 2).round(1),
        genre = getGenres(genreIds)
    )
}

fun MovieDetailsEntity.mapToDomain(): MovieDetails {
    return MovieDetails(
        id = this.id,
        title = this.title,
        posterUrl = IMAGE_URL + this.posterPath,
        rating = (this.rating / 2).round(1),
        description = this.description,
        releaseDate = this.releaseDate,
        voteCount = this.voteCount,
        genres = this.genres.map(GenreEntity::mapToDomain)
    )
}

fun GenreEntity.mapToDomain(): String {
    return this.name
}

fun VideoEntity.mapToDomain(): Video {
    return Video(
        type = this.type,
        name = this.name,
        url = YOUTUBE_URL + this.key
    )
}

fun VideoEntity.mapToTrailer(): Trailer {
    return Trailer(
        name = this.name,
        url = YOUTUBE_URL + this.key
    )
}

fun WatchListMovieEntity.mapToDomain(): Movie {
    return Movie(
        id = this.movie_id,
        title = this.title,
        posterUrl = IMAGE_URL + this.posterPath,
        rating = null,
        genre = this.genre
    )
}

fun Movie.mapToWatchListEntity(): WatchListMovieEntity {
    return WatchListMovieEntity(
        movie_id = this.id,
        is_favorite = false,
        posterPath = this.posterUrl.removePrefix(YOUTUBE_URL),
        title = this.title,
        genre = this.genre
    )
}

private fun getGenres(ids: List<Int>): String {
    return when(ids.size) {
        0 -> ""
        1 -> genres[ids[0]] + ""
        else -> genres[ids[0]] + ", " + genres[ids[1]]
    }
}