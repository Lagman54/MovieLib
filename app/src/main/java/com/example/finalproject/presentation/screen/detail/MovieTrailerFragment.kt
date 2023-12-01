package com.example.finalproject.presentation.screen.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.finalproject.databinding.FragmentTrailersBinding
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.screen.detail.adapter.TrailerAdapter


class MovieTrailerFragment : Fragment() {

    private lateinit var binding: FragmentTrailersBinding
    private lateinit var adapter: TrailerAdapter
    private val viewModel: MovieDetailsViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrailersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        setUpViewModel()
    }

    private fun setUpAdapter() = with(binding) {
        adapter = TrailerAdapter()
        adapter.onClick = { url ->
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }
        list.addItemDecoration(OffsetDecoration(bottom = 8))
        list.adapter = adapter
    }

    private fun setUpViewModel() {
        viewModel.trailers.observe(viewLifecycleOwner) {
            if(it.isEmpty()) {
                binding.noTrailersAvailableText.visibility = View.VISIBLE
            } else {
                adapter.submitList(it)
            }
        }
    }

}