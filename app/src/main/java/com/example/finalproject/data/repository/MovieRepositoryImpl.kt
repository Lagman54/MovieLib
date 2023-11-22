package com.example.finalproject.data.repository

import android.accounts.NetworkErrorException
import com.example.finalproject.data.MovieApi
import com.example.finalproject.data.model.MovieDetailsEntity
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.data.model.VideoEntity
import com.example.finalproject.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): List<MovieEntity> {
        return api.getMovies(1).movies.map(MovieEntity::mapToFullPosterPath)
    }

    override suspend fun getMovie(id: Int): MovieDetailsEntity {
        return api.getMovieDetails(id).mapToFullPosterPath()
    }

    override suspend fun getVideos(id: Int): List<VideoEntity> {
        return api.getMovieVideos(id).videos
    }

}

fun MovieEntity.mapToFullPosterPath() =
    MovieEntity(
        id = this.id,
        title = this.title,
        posterPath = "https://image.tmdb.org/t/p/original/" + this.posterPath,
        genreIds = this.genreIds,
        rating = this.rating
    )

fun MovieDetailsEntity.mapToFullPosterPath() =
    MovieDetailsEntity(
        id = this.id,
        title = this.title,
        posterPath = "https://image.tmdb.org/t/p/original/" + this.posterPath,
        genres = this.genres,
        rating = this.rating,
        description = this.description,
        releaseDate = this.releaseDate,
        voteCount = this.voteCount
    )

