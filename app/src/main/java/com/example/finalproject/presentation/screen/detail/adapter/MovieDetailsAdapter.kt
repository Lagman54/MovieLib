package com.example.finalproject.presentation.screen.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemDetailsBodyBinding
import com.example.finalproject.databinding.ItemDetailsHeaderBinding
import com.example.finalproject.databinding.ItemDetailsViewpagerBinding
import com.example.finalproject.presentation.screen.detail.adapter.ListItem.Companion.BODY
import com.example.finalproject.presentation.screen.detail.adapter.ListItem.Companion.HEADER
import com.example.finalproject.presentation.screen.detail.adapter.ListItem.Companion.VIEWPAGER

class MovieDetailsAdapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(DetailsDiffUtils) {

    override fun getItemViewType(position: Int) = currentList[position].itemType

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> HeaderViewHolder(
                ItemDetailsHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            BODY -> BodyViewHolder(
                ItemDetailsBodyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEWPAGER -> ViewPagerViewHolder(
                ItemDetailsViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid type of item")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            HEADER -> (holder as HeaderViewHolder).bind(currentList[position] as ListItem.Header)
            BODY -> (holder as BodyViewHolder).bind(currentList[position] as ListItem.Body)
            VIEWPAGER -> (holder as ViewPagerViewHolder).bind(currentList[position] as ListItem.ViewPager)
        }
    }

    object DetailsDiffUtils : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.itemType == newItem.itemType
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.itemType == newItem.itemType
        }

    }

}
