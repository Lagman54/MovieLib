package com.example.finalproject.presentation.screen.browse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.finalproject.databinding.FragmentGridMoviesBinding
import com.example.finalproject.presentation.base.BaseFragment
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.image_loader.ImageLoader
import com.example.finalproject.presentation.screen.browse.adapter.VerticalMoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PopularFragment : BaseFragment() {

    private lateinit var binding: FragmentGridMoviesBinding
    private lateinit var pagingAdapter: VerticalMoviePagingAdapter
    private val viewModel: BrowseViewModel by viewModels()

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
        pagingAdapter = VerticalMoviePagingAdapter(imageLoader)
        pagingAdapter.onClick = navigateToMovieDetails()

        list.addItemDecoration(OffsetDecoration(end = 8, bottom = 16))
        list.adapter = pagingAdapter
    }

    private fun setUpViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collectLatest { pagingData ->
                    pagingAdapter.submitData(pagingData)
                }
            }
        }
    }

}