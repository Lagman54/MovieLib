package com.example.finalproject.presentation.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.model.MovieEntity
import com.example.finalproject.domain.repository.MovieRepository
import com.example.finalproject.data.mapper.mapToDomain
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

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
        viewModelScope.launch(Dispatchers.IO) {
            val movies = repository.getPopularMovies()
            val trendingMovie = repository.getMovie(movies[0].id)
            withContext(Dispatchers.Main) {
                _userRecommendations.value =
                    movies
                        .subList(1, 12)
                _trendingMovie.value = trendingMovie
            }
        }
    }

    fun getTrendingMovieTrailer(id: Int) {
    }

}