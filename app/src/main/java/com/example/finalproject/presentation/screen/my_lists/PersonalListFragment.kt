package com.example.finalproject.presentation.screen.my_lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.presentation.adapter_common.HorizontalMovieAdapter
import com.example.finalproject.databinding.FragmentPersonalListBinding
import com.example.finalproject.presentation.OnMovieClickListener
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.screen.detail.MovieDetailsFragment
import com.example.finalproject.domain.model.Movie

class PersonalListFragment : Fragment() {

    private lateinit var binding: FragmentPersonalListBinding
    private lateinit var adapter: HorizontalMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "list1"
        binding.toolbar.onDrawableStartClick = {
            findNavController().popBackStack()
        }

        adapter = HorizontalMovieAdapter()
        adapter.onClick = OnMovieClickListener { movieId ->
            findNavController().navigate(
                R.id.action_personalListFragment_to_movieDetailsFragment2,
                MovieDetailsFragment.createBundle(id = movieId)
            )
        }

        binding.list.adapter = adapter
        binding.list.addItemDecoration(OffsetDecoration(start = 16, end = 16, bottom = 16))

        adapter.submitList(listOf(
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
        ))
    }

    companion object {
        private const val LIST_ID = "id"
        fun createBundle(id: Int): Bundle {
            return bundleOf(
                LIST_ID to id
            )
        }
    }
}