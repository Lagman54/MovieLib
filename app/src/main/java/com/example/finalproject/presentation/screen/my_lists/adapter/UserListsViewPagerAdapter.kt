package com.example.finalproject.presentation.screen.my_lists.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.finalproject.presentation.screen.my_lists.PersonalFragment
import com.example.finalproject.presentation.screen.my_lists.WatchListFragment

class UserListsViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> WatchListFragment()
            1 -> PersonalFragment()
            else -> WatchListFragment()
        }
    }

}