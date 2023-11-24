package com.example.finalproject.data.repository

import android.accounts.NetworkErrorException
import com.example.finalproject.data.MovieApi
import com.example.finalproject.data.mapper.mapToDomain
import com.example.finalproject.data.model.MovieDetailsEntity
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.data.model.VideoEntity
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Video
import com.example.finalproject.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return api.getMovies(1).movies.map(MovieEntity::mapToDomain)
    }

    override suspend fun getMovie(id: Int): MovieDetails {
        return api.getMovieDetails(id).mapToDomain()
    }

    override suspend fun getVideos(id: Int): List<Video> {
        return api.getMovieVideos(id).videos.map(VideoEntity::mapToDomain)
    }

}