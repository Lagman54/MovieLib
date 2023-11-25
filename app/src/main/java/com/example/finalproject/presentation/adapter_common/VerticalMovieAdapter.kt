package com.example.finalproject.presentation.adapter_common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemVerticalMovieBinding
import com.example.finalproject.presentation.image_loader.ImageLoader
import com.example.finalproject.domain.model.Movie


class VerticalMovieAdapter (
    private val imageLoader: ImageLoader
) : ListAdapter<Movie, VerticalMovieAdapter.ViewHolder>(DiffUtilCallback) {

    var onClick: OnMovieClickListener? = null

    class ViewHolder(
        private val binding: ItemVerticalMovieBinding,
        private val onClick: OnMovieClickListener?,
        private val imageLoader: ImageLoader
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: Movie) = with(binding) {
            imageLoader.load(posterImage, listItem.posterUrl)

            title.text = listItem.title
            root.setOnClickListener {
                onClick?.click(listItem.id)
            }
        }
    }

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVerticalMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
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