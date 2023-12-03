package com.example.finalproject.presentation.screen.search

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.repository.MovieRepository
import com.example.finalproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    var movies: Flow<PagingData<Movie>> = flowOf()

    fun search(text: String) {
        movies = repository.getMoviesByTitle(text).cachedIn(viewModelScope)
    }

}