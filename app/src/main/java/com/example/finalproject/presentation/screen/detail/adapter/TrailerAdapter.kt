package com.example.finalproject.presentation.screen.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemTrailerBinding
import com.example.finalproject.domain.model.Trailer

class TrailerAdapter : ListAdapter<Trailer, TrailerAdapter.ViewHolder>(TrailersDiffUtils) {

    var onClick: ((url: String) -> Unit)? = null

    class ViewHolder(
        private val binding: ItemTrailerBinding,
        private val onClick: ((url: String) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: Trailer) {
            binding.title.text = listItem.name
            binding.root.setOnClickListener {
                onClick?.invoke(listItem.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTrailerBinding.inflate(
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

    object TrailersDiffUtils : DiffUtil.ItemCallback<Trailer>() {
        override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
            return oldItem == newItem
        }

    }

}