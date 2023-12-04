package com.example.finalproject.presentation.screen.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.finalproject.presentation.screen.detail.MovieTrailerFragment
import com.example.finalproject.presentation.screen.detail.SimilarMoviesFragment
import com.example.finalproject.presentation.screen.my_lists.PersonalListFragment

class MovieDetailsViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SimilarMoviesFragment()
            else -> MovieTrailerFragment()
        }
    }

}