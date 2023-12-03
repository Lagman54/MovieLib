package com.example.finalproject.presentation.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.finalproject.databinding.FragmentGridMoviesBinding
import com.example.finalproject.presentation.base.BaseFragment
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.image_loader.ImageLoader
import com.example.finalproject.presentation.screen.detail.adapter.MovieLargeAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SimilarMoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentGridMoviesBinding
    private lateinit var adapter: MovieLargeAdapter
    private val viewModel: MovieDetailsViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGridMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpViewModel()
    }

    private fun setUpAdapter() = with(binding) {
        adapter = MovieLargeAdapter(imageLoader)
        adapter.onClick = onMovieClickListener()

        list.addItemDecoration(OffsetDecoration(end = 8, bottom = 16))

        list.adapter = adapter
    }

    private fun setUpViewModel() {
        viewModel.similarMovies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}