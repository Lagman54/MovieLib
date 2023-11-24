package com.example.finalproject.presentation.screen.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemDetailsBodyBinding
import com.example.finalproject.databinding.ItemDetailsHeaderBinding
import com.example.finalproject.databinding.ItemDetailsViewpagerBinding
import com.example.finalproject.presentation.screen.detail.adapter.ListItem.Companion.BODY
import com.example.finalproject.presentation.screen.detail.adapter.ListItem.Companion.HEADER
import com.example.finalproject.presentation.screen.detail.adapter.ListItem.Companion.VIEWPAGER

class MovieDetailsAdapter(
    private val itemList: MutableList<ListItem> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int) = itemList[position].itemType

    override fun getItemCount() = itemList.size

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
            HEADER -> (holder as HeaderViewHolder).bind(itemList[position] as ListItem.Header)
            BODY -> (holder as BodyViewHolder).bind(itemList[position] as ListItem.Body)
            VIEWPAGER -> (holder as ViewPagerViewHolder).bind(itemList[position] as ListItem.ViewPager)
        }
    }

    fun update(list: List<ListItem>) {
        itemList.clear()
        itemList.addAll(list)
        notifyItemRangeInserted(0, list.size)
    }

}
