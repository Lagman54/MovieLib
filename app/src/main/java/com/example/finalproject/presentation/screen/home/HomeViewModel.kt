package com.example.finalproject.presentation.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.domain.repository.MovieRepository
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Video
import com.example.finalproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val _userRecommendations = MutableLiveData<List<Movie>>()
    val userRecommendations: LiveData<List<Movie>>
        get() = _userRecommendations

    private val _trendingMovie = MutableLiveData<MovieDetails>()
    val trendingMovie: LiveData<MovieDetails>
        get() = _trendingMovie

    private val _trendingMovieTrailer = MutableLiveData<Video>()
    private val trendingMovieTrailer: LiveData<Video>
        get() = _trendingMovieTrailer

    fun getRecommendations() {

        launch(
            doubleRequest = {
                val movies = repository.getPopularMovies()
                val trendingMovieDetails = repository.getMovie(movies[0].id)
                Pair(movies, trendingMovieDetails)
            },
            onSuccess = { movies, trendingMovieDetails ->
                _userRecommendations.value = movies.subList(1, 12)
                _trendingMovie.value = trendingMovieDetails
            }
        )

    }

    fun getTrendingMovieTrailer(id: Int) {
    }

}