package com.example.finalproject.presentation.screen.my_lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalproject.databinding.FragmentWatchListBinding
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.presentation.adapter_common.HorizontalMovieAdapter
import com.example.finalproject.presentation.base.BaseFragment
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.image_loader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WatchListFragment : BaseFragment() {

    private lateinit var binding: FragmentWatchListBinding
    private lateinit var adapter: HorizontalMovieAdapter

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
        adapter = HorizontalMovieAdapter(imageLoader)

        adapter.onClick = onMovieClickListener()

        adapter.submitList(
            listOf(
                Movie(),
                Movie(),
                Movie(),
                Movie(),
                Movie(),
                Movie(),
                Movie(),
                Movie(),
                Movie(),
            )
        )

        binding.watchList.addItemDecoration(OffsetDecoration(start = 16, end = 16, bottom = 16))
        binding.watchList.adapter = adapter

    }

}