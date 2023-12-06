package com.example.finalproject.presentation.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.domain.repository.MovieRepository
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.domain.model.Trailer
import com.example.finalproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieRepository) :
    BaseViewModel() {

    var id: Int = 0
        set(value) {
            field = value
            inWatchList(value)
            getMovieDetails(value)
            getSimilarMovies(value)
            getTrailers(value)
        }

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    private val _trailers = MutableLiveData<List<Trailer>>()
    val trailers: LiveData<List<Trailer>>
        get() = _trailers

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>>
        get() = _similarMovies

    private val _inWatchList = MutableLiveData<Boolean>()
    val inWatchList: LiveData<Boolean>
        get() = _inWatchList

    private fun getMovieDetails(id: Int) {
        launch(
            request = {
                repository.getMovie(id)
            },
            onSuccess = {
                _movieDetails.value = it
            }
        )
    }

    private fun getTrailers(id: Int) {
        launch(
            request = {
                repository.getTrailers(id)
            },
            onSuccess = {
                _trailers.value = it
            }
        )
    }

    private fun getSimilarMovies(id: Int) {
        launch(
            request = {
                repository.getSimilarMovies(id)
            },
            onSuccess = {
                _similarMovies.value = it.subList(0, 10)
            }
        )
    }

    private fun inWatchList(id: Int) {
        launch(
            request = {
                repository.inWatchList(id)
            },
            onSuccess = {
                _inWatchList.value = it
            }
        )
    }

    private fun MovieDetails.mapToMovie(): Movie {
        return Movie(
            id = this.id,
            title = this.title,
            posterUrl = this.posterUrl,
            rating = this.rating,
            genre = this.genres.reduce { acc, s -> "$acc, $s" }
        )
    }

    fun addRemoveWatchList() {
        launch(
            request = {
                if(_inWatchList.value == false) {
                    repository.addToWatchList(_movieDetails.value!!.mapToMovie())
                    return@launch true
                }
                else {
                    repository.removeFromWatchList(id)
                    return@launch false
                }
            }, onSuccess = {
                _inWatchList.value = it
            }
        )
    }

}