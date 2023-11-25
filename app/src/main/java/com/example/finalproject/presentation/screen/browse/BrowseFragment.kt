package com.example.finalproject.presentation.screen.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentBrowseScreenBinding
import com.example.finalproject.presentation.adapter_common.OnMovieClickListener
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.image_loader.ImageLoader
import com.example.finalproject.presentation.screen.browse.adapter.BrowseViewPagerAdapter
import com.example.finalproject.presentation.screen.browse.adapter.MoviePagingAdapter
import com.example.finalproject.presentation.screen.detail.MovieDetailsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class BrowseFragment : Fragment() {

    private lateinit var binding: FragmentBrowseScreenBinding
    private lateinit var viewPagerAdapter: BrowseViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrowseScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }

    private fun setUpViewPager() = with(binding) {
        viewPagerAdapter = BrowseViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayoutBinding.topTab, viewPager) { tab, pos ->
            when(pos) {
                0 -> tab.text = getString(R.string.popular).uppercase()
                1 -> tab.text = getString(R.string.movie_genres).uppercase()
            }
        }.attach()
    }
}