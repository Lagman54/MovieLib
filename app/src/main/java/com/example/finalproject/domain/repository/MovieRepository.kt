package com.example.finalproject.domain.repository

import androidx.paging.PagingData
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getMovie(id: Int): MovieDetails

    suspend fun getVideos(id: Int): List<Video>

    fun getMovies(): Flow<PagingData<Movie>>
}