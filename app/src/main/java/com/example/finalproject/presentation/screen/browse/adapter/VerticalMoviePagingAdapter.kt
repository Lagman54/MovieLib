package com.example.finalproject.presentation.screen.browse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemMovieLargeBinding
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.presentation.adapter_common.OnMovieClickListener
import com.example.finalproject.presentation.image_loader.ImageLoader

class VerticalMoviePagingAdapter(
    private val imageLoader: ImageLoader
) : PagingDataAdapter<Movie, VerticalMoviePagingAdapter.ViewHolder>(MovieComparator) {

    var onClick: OnMovieClickListener? = null

    class ViewHolder(private val binding: ItemMovieLargeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listItem: Movie,
            onClick: OnMovieClickListener?,
            imageLoader: ImageLoader
        ) = with(binding) {
            title.text = listItem.title
            genre.text = listItem.genre
            imageLoader.load(posterImage, listItem.posterUrl)

            moreButton.setOnClickListener {

            }
            root.setOnClickListener {
                onClick?.click(listItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieLargeBinding.inflate(inflater, parent, false)
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