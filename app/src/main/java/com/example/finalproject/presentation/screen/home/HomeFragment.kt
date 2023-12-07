package com.example.finalproject.presentation.screen.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.presentation.adapter_common.VerticalMovieAdapter
import com.example.finalproject.databinding.FragmentHomeScreenBinding
import com.example.finalproject.presentation.base.BaseFragment
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.screen.detail.MovieDetailsFragment
import com.example.finalproject.presentation.image_loader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var playingMoviesAdapter: VerticalMovieAdapter
    private lateinit var popularAdapter: VerticalMovieAdapter
    private lateinit var watchListAdapter: VerticalMovieAdapter
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
        hideView()
        setUpAdapters()
        setUpViewModel()
    }

    private fun hideView() = with(binding) {
        homeScrollView.visibility = View.INVISIBLE
    }

    private fun showView() = with(binding) {
        progressBar.visibility = View.GONE
        homeScrollView.visibility = View.VISIBLE
    }

    private fun setUpAdapters() = with(binding) {
        playingMoviesAdapter = VerticalMovieAdapter(imageLoader)
        popularAdapter = VerticalMovieAdapter(imageLoader)
        watchListAdapter = VerticalMovieAdapter(imageLoader)

        playingMoviesAdapter.onClick = navigateToMovieDetails()
        popularAdapter.onClick = navigateToMovieDetails()
        watchListAdapter.onClick = navigateToMovieDetails()

        recommendationsList.addItemDecoration(OffsetDecoration(start = 8))
        popularList.addItemDecoration(OffsetDecoration(start = 8))
        watchList.addItemDecoration(OffsetDecoration(start = 8))

        recommendationsList.adapter = playingMoviesAdapter
        popularList.adapter = popularAdapter
        watchList.adapter = watchListAdapter
    }

    private fun setUpViewModel() = with(binding) {
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            popularAdapter.submitList(it)
        }

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            playingMoviesAdapter.submitList(it)
        }

        viewModel.trendingMovie.observe(viewLifecycleOwner) { movieDetails ->
            imageLoader.load(trendingMoviePoster, movieDetails.posterUrl)
            trendingMovieTitle.text = movieDetails.title
            trendingMovieDescription.text = movieDetails.description

            trendingMoviePoster.setOnClickListener {
                findNavController().navigate(
                    R.id.action_global_movieDetailsFragment4,
                    MovieDetailsFragment.createBundle(id = movieDetails.id)
                )
            }

            viewModel.getTrendingMovieTrailer(movieDetails.id)

            showView()
        }

        viewModel.trendingMovieTrailer.observe(viewLifecycleOwner) { trailer ->
            watchTrailerButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, trailer.url.toUri()))
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.watchList.collectLatest {
                    watchListAdapter.submitList(it)
                }
            }
        }

        viewModel.getRecommendations()
        viewModel.getNowPlayingMovies()
    }

}