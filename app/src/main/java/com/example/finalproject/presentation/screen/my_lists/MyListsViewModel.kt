package com.example.finalproject.presentation.screen.my_lists

import com.example.finalproject.domain.model.Movie
import com.example.finalproject.domain.repository.MovieRepository
import com.example.finalproject.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MyListsViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    val watchList: Flow<List<Movie>> = repository.getWatchListMovies()

}