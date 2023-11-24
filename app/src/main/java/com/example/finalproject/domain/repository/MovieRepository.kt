package com.example.finalproject.domain.repository

import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Video

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getMovie(id: Int): MovieDetails

    suspend fun getVideos(id: Int): List<Video>
}