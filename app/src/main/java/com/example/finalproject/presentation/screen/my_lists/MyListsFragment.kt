package com.example.finalproject.presentation.screen.my_lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.presentation.screen.my_lists.adapter.UserListsViewPagerAdapter
import com.example.finalproject.databinding.FragmentMyListsBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListsFragment : Fragment() {

    private lateinit var binding: FragmentMyListsBinding
    private lateinit var adapter: UserListsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserListsViewPagerAdapter(childFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 2

        TabLayoutMediator(binding.tabLayoutBinding.topTab, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.watchlist)
                1 -> tab.text = getString(R.string.personal)
                2 -> tab.text = getString(R.string.history)
            }

        }.attach()
    }

}