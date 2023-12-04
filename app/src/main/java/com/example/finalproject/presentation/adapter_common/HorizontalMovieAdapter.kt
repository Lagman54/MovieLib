package com.example.finalproject.presentation.adapter_common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemHorizontalMovieBinding
import com.example.finalproject.domain.model.Movie
import com.example.finalproject.presentation.image_loader.ImageLoader

class HorizontalMovieAdapter(
    private val imageLoader: ImageLoader
) :
    ListAdapter<Movie, HorizontalMovieAdapter.ViewHolder>(DiffUtilCallback) {

    var onClick: OnMovieClickListener? = null

    class ViewHolder(
        private val binding: ItemHorizontalMovieBinding,
        private val onClick: OnMovieClickListener?,
        private val imageLoader: ImageLoader
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: Movie) = with(binding) {
            imageLoader.load(posterImage, listItem.posterUrl)
            title.text = listItem.title
            genres.text = listItem.genre
            if(listItem.rating != null)
                rating.text = listItem.rating.toString()

            root.setOnClickListener {
                onClick?.click(listItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHorizontalMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick,
            imageLoader
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}