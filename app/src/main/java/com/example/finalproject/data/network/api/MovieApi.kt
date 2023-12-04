package com.example.finalproject.data.network.api

import com.example.finalproject.data.network.model.MovieDetailsEntity
import com.example.finalproject.data.network.model.MovieResponse
import com.example.finalproject.data.network.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(
        "/3/discover/movie" + "?include_adult=false" + "&sort_by=popularity.desc"
    )
    suspend fun getMovies(@Query("page") page: Int): MovieResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): MovieDetailsEntity

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") id: Int): VideoResponse

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): MovieResponse

    @GET("/3/search/movie" + "?include_adult=false")
    suspend fun getMoviesByTitle(
        @Query("query") title: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): MovieResponse

}