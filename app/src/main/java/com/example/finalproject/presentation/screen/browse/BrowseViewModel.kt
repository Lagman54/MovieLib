package com.example.finalproject.presentation.screen.browse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = repository.getMovies()
        .cachedIn(viewModelScope)

}