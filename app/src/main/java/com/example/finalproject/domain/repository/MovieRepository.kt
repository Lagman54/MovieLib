package com.example.finalproject.domain.repository

import com.example.finalproject.data.model.MovieDetailsEntity
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.data.repository.mapToFullPosterPath

interface MovieRepository {

    suspend fun getPopularMovies(): List<MovieEntity>

    suspend fun getMovie(id: Int): MovieDetailsEntity
}