package com.example.finalproject.presentation.screen.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemHorizontalMovieBinding
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.presentation.adapter_common.OnMovieClickListener
import com.example.finalproject.presentation.image_loader.ImageLoader

class HorizontalMoviePagingAdapter(
    private val imageLoader: ImageLoader
) : PagingDataAdapter<Movie, HorizontalMoviePagingAdapter.ViewHolder>(MovieComparator) {

    var onClick: OnMovieClickListener? = null

    class ViewHolder(private val binding: ItemHorizontalMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listItem: Movie,
            onClick: OnMovieClickListener?,
            imageLoader: ImageLoader
        ) = with(binding) {
            imageLoader.load(posterImage, listItem.posterUrl)
            title.text = listItem.title
            genres.text = listItem.genre
            rating.text = "Average: ${listItem.rating}"

            favoriteButton.setOnClickListener {

            }
            moreButton.setOnClickListener {

            }
            root.setOnClickListener {
                onClick?.click(listItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHorizontalMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, onClick, imageLoader)
    }

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}