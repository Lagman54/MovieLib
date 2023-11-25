package com.example.finalproject.presentation.screen.browse.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.finalproject.presentation.screen.browse.PopularFragment
import com.example.finalproject.presentation.screen.my_lists.PersonalFragment
import com.example.finalproject.presentation.screen.my_lists.WatchListFragment

class BrowseViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PopularFragment()
            else -> PopularFragment()
        }
    }

}