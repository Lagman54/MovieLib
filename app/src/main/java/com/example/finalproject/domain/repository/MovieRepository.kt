package com.example.finalproject.domain.repository

import com.example.finalproject.data.model.MovieDetailsEntity
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.data.model.VideoEntity

interface MovieRepository {

    suspend fun getPopularMovies(): List<MovieEntity>

    suspend fun getMovie(id: Int): MovieDetailsEntity

    suspend fun getVideos(id: Int): List<VideoEntity>
}