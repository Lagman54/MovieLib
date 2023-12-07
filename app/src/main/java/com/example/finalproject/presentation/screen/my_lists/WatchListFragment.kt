package com.example.finalproject.presentation.screen.my_lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.finalproject.databinding.FragmentWatchListBinding
import com.example.finalproject.presentation.adapter_common.HorizontalMovieAdapter
import com.example.finalproject.presentation.base.BaseFragment
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.image_loader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WatchListFragment : BaseFragment() {

    private lateinit var binding: FragmentWatchListBinding
    private lateinit var adapter: HorizontalMovieAdapter
    private val viewModel: MyListsViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpViewModel()
    }

    private fun setUpAdapter() = with(binding) {
        adapter = HorizontalMovieAdapter(imageLoader)
        adapter.onClick = navigateToMovieDetails()
        watchList.addItemDecoration(OffsetDecoration(start = 16, end = 16, bottom = 16))
        watchList.adapter = adapter
    }

    private fun setUpViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.watchList.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
    }
}