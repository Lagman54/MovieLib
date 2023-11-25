package com.example.finalproject.presentation.screen.home

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
import com.example.finalproject.presentation.adapter_common.VerticalMovieAdapter
import com.example.finalproject.databinding.FragmentHomeScreenBinding
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.presentation.adapter_common.OnMovieClickListener
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.screen.detail.MovieDetailsFragment
import com.example.finalproject.presentation.image_loader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
        setUpAdapters()
        setUpViewModel()
    }

    private fun setUpAdapters() = with(binding) {
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
    }

    private fun setUpViewModel() = with(binding) {
        viewModel.userRecommendations.observe(viewLifecycleOwner) {
            recommendationsAdapter.submitList(it)
        }

        viewModel.trendingMovie.observe(viewLifecycleOwner) { movieDetails ->
            imageLoader.load(trendingMoviePoster, movieDetails.posterUrl)
            trendingMovieTitle.text = movieDetails.title
            trendingMovieDescription.text = movieDetails.description

            trendingMoviePoster.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_movieDetailsFragment,
                    MovieDetailsFragment.createBundle(id = movieDetails.id)
                )
            }

            viewModel.getTrendingMovieTrailer(movieDetails.id)
        }

        viewModel.trendingMovieTrailer.observe(viewLifecycleOwner) { trailer ->
            watchTrailerButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, trailer.url.toUri()))
            }
        }

        viewModel.getRecommendations()
    }



}