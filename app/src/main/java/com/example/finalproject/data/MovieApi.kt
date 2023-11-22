package com.example.finalproject.data

import com.example.finalproject.data.model.MovieDetailsEntity
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.data.model.MovieResponse
import com.example.finalproject.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(
        "/3/discover/movie" + "?include_adult=false" + "&language=en-US" + "&sort_by=popularity.desc"
    )
    suspend fun getMovies(@Query("page") page: Int): MovieResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): MovieDetailsEntity

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") id: Int): VideoResponse


}