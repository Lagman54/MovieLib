package com.example.finalproject.presentation.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentMovieDetailsBinding
import com.example.finalproject.presentation.image_loader.ImageLoader
import com.example.finalproject.domain.model.MovieDetails
import com.example.finalproject.presentation.screen.detail.adapter.ListItem
import com.example.finalproject.presentation.screen.detail.adapter.MovieDetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var adapter: MovieDetailsAdapter
    private val viewModel: MovieDetailsViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.onDrawableStartClick = { findNavController().popBackStack() }
        setUpAdapter()
        setUpViewModel()
    }

    private fun setUpAdapter() = with(binding) {
        adapter = MovieDetailsAdapter()
        list.adapter = adapter
    }

    private fun setUpViewModel() {
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            imageLoader.load(binding.posterImage, it.posterUrl)
            adapter.submitList(transformData(it))
        }
        viewModel.id = requireArguments().getInt(MOVIE_ID)
    }

    private fun transformData(movie: MovieDetails) = listOf(
        ListItem.Header(movie.title),
        ListItem.Body(movie.rating, movie.description),
        ListItem.ViewPager(movie.id, childFragmentManager, lifecycle)
    )

    companion object {
        private const val MOVIE_ID = "id"
        fun createBundle(id: Int): Bundle {
            return bundleOf(
                MOVIE_ID to id
            )
        }
    }

}
