package com.example.finalproject.presentation.adapter_common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemHorizontalMovieBinding
import com.example.finalproject.presentation.OnMovieClickListener
import com.example.finalproject.presentation.model.Movie

class HorizontalMovieAdapter :
    ListAdapter<Movie, HorizontalMovieAdapter.ViewHolder>(DiffUtilCallback) {

    var onClick: OnMovieClickListener? = null

    class ViewHolder(
        private val binding: ItemHorizontalMovieBinding,
        private val onClick: OnMovieClickListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: Movie) = with(binding) {
            title.text = listItem.title
            posterImage.setImageResource(listItem.posterRes)
            genres.text = listItem.genre
            rating.text = listItem.rating.toString()

            root.setOnClickListener {
                onClick?.click(listItem.id)
            }
        }
    }

    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHorizontalMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
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