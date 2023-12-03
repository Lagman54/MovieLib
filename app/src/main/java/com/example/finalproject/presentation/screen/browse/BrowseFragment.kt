package com.example.finalproject.presentation.screen.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentBrowseScreenBinding
import com.example.finalproject.presentation.screen.browse.adapter.BrowseViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


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