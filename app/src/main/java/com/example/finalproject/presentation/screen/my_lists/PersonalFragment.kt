package com.example.finalproject.presentation.screen.my_lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.presentation.screen.my_lists.adapter.PersonalAdapter
import com.example.finalproject.databinding.FragmentPersonalBinding
import com.example.finalproject.presentation.adapter_common.OnMovieClickListener
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.domain.model.PersonalListItem


class PersonalFragment : Fragment() {

    private lateinit var binding: FragmentPersonalBinding
    private lateinit var adapter: PersonalAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PersonalAdapter()
        binding.list.adapter = adapter
        binding.list.addItemDecoration(OffsetDecoration(start = 16, end = 16, bottom = 16))

        adapter.onClick = OnMovieClickListener { listId ->
            findNavController().navigate(
                R.id.action_myListsFragment_to_personalListFragment,
                PersonalListFragment.createBundle(listId)
            )
        }

        adapter.submitList(getData())
    }

    private fun getData(): List<PersonalListItem> = listOf(
        PersonalListItem(0, "Favorites", "0 items, Updated on 15 Nov"),
        PersonalListItem(0, "list1", "0 items, Updated on 15 Nov"),
        PersonalListItem(0, "list2", "0 items, Updated on 15 Nov"),
    )

}