package com.example.finalproject.presentation.screen.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentSearchScreenBinding
import com.example.finalproject.presentation.base.BaseFragment
import com.example.finalproject.presentation.decoration.OffsetDecoration
import com.example.finalproject.presentation.image_loader.ImageLoader
import com.example.finalproject.presentation.screen.search.adapter.HorizontalMoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchScreenBinding
    private lateinit var pagingAdapter: HorizontalMoviePagingAdapter
    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpAdapter()
        setUpViewModel()
    }

    private fun setUpView() = with(binding){
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        editText.requestFocus()
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setUpAdapter() = with(binding) {
        pagingAdapter = HorizontalMoviePagingAdapter(imageLoader)
        pagingAdapter.onClick = navigateToMovieDetails()

        list.addItemDecoration(OffsetDecoration(start = 16, end = 16, top = 16))
        list.adapter = pagingAdapter
    }

    private fun setUpViewModel() {
        binding.editText.addTextChangedListener {
            val text = it.toString()
            viewModel.search(text)
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.movies.collectLatest {
                        pagingAdapter.submitData(it)
                    }
                }
            }
        }
    }
}