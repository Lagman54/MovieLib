package com.example.finalproject.presentation.screen.my_lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.presentation.adapter_common.HorizontalMovieAdapter
import com.example.finalproject.databinding.FragmentWatchListBinding
import com.example.finalproject.presentation.adapter_common.OnMovieClickListener
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.screen.detail.MovieDetailsFragment
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.presentation.image_loader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WatchListFragment : Fragment() {

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

        adapter.onClick = OnMovieClickListener { movieId ->
            findNavController().navigate(
                R.id.action_global_movieDetailsFragment4,
                MovieDetailsFragment.createBundle(id = movieId)
            )
        }

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