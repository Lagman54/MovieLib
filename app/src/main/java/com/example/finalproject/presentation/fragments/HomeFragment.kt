package com.example.finalproject.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.presentation.adapter.VerticalMovieAdapter
import com.example.finalproject.databinding.FragmentHomeScreenBinding
import com.example.finalproject.presentation.OnMovieClickListener
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.image.ImageLoader
import com.example.finalproject.presentation.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO try to come up with BaseFragment, because you have too much identical code between fragments

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var recommendationsAdapter: VerticalMovieAdapter
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTrendingMovie()
        setUpLists()
    }

    private fun setUpTrendingMovie() = with(binding) {

        viewModel.trendingMovie.observe(viewLifecycleOwner) {movieDetails ->
            imageLoader.load(trendingMoviePoster, movieDetails.posterUrl)
            trendingMovieTitle.text = movieDetails.title
            trendingMovieDescription.text = movieDetails.description

            trendingMoviePoster.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_movieDetailsFragment,
                    MovieDetailsFragment.createBundle(id = movieDetails.id)
                )
            }
        }

    }

    private fun setUpLists() = with(binding) {

        recommendationsAdapter = VerticalMovieAdapter(imageLoader)
        recommendationsAdapter.onClick = OnMovieClickListener { movieId ->
            findNavController().navigate(
                R.id.action_homeFragment_to_movieDetailsFragment,
                MovieDetailsFragment.createBundle(id = movieId)
            )
        }

        recommendationsList.addItemDecoration(OffsetDecoration(start = 8))
        popularList.addItemDecoration(OffsetDecoration(start = 8))
        watchList.addItemDecoration(OffsetDecoration(start = 8))

        recommendationsList.adapter = recommendationsAdapter
        popularList.adapter = recommendationsAdapter
        watchList.adapter = recommendationsAdapter

        viewModel.userRecommendations.observe(viewLifecycleOwner) {
            recommendationsAdapter.submitList(it)
        }

        viewModel.getRecommendations()
    }

}