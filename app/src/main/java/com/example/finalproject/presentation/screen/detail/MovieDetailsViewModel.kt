package com.example.finalproject.presentation.screen.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.domain.repository.MovieRepository
import com.example.finalproject.data.mapper.mapToDomain
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieRepository) :
    BaseViewModel() {

    var id: Int = 0
        set(value) {
            getMovieDetails(value)
            getSimilarMovies(value)
            field = value
        }

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>>
        get() = _similarMovies

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

    private fun getSimilarMovies(id: Int) {
        launch(
            request = {
                repository.getSimilarMovies(id)
            },
            onSuccess = {
                _similarMovies.value = it
            }
        )
    }

}