package com.example.finalproject.presentation.base

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.presentation.adapter_common.OnMovieClickListener
import com.example.finalproject.presentation.screen.detail.MovieDetailsFragment

open class BaseFragment : Fragment() {

    protected fun navigateToMovieDetails() = OnMovieClickListener { movieId ->
        findNavController().navigate(
            R.id.action_global_movieDetailsFragment4,
            MovieDetailsFragment.createBundle(id = movieId)
        )
    }

}