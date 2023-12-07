package com.example.finalproject.presentation.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.domain.repository.MovieRepository
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Trailer
import com.example.finalproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>>
        get() = _nowPlayingMovies

    private val _trendingMovie = MutableLiveData<MovieDetails>()
    val trendingMovie: LiveData<MovieDetails>
        get() = _trendingMovie

    private val _trendingMovieTrailer = MutableLiveData<Trailer>()
    val trendingMovieTrailer: LiveData<Trailer>
        get() = _trendingMovieTrailer

    val watchList: Flow<List<Movie>> = repository.getWatchListMovies()

    fun getRecommendations() {
        launch(
            doubleRequest = {
                val movies = repository.getPopularMovies()
                val trendingMovieDetails = repository.getMovie(movies[0].id)
                Pair(movies, trendingMovieDetails)
            },
            onSuccess = { movies, trendingMovieDetails ->
                _popularMovies.value = movies.subList(1, 12)
                _trendingMovie.value = trendingMovieDetails
            }
        )

    }

    fun getNowPlayingMovies() {
        launch(
            request = {
                repository.getNowPlayingMovies()
            },
            onSuccess = { movies ->
                _nowPlayingMovies.value = movies.subList(0, 12)
            }
        )
    }

    fun getTrendingMovieTrailer(id: Int) {
        launch(
            request = {
                repository.getTrailers(id)
            },
            onSuccess = {
                // TODO handle the case when there are no trailers available
                if (it.isEmpty()) {
                    return@launch
                }
                _trendingMovieTrailer.value = it[0]
            }
        )
    }

}