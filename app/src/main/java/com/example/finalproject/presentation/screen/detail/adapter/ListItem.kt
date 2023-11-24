package com.example.finalproject.presentation.screen.detail.adapter

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

sealed class ListItem {

    abstract val itemType: Int

    data class Header(
        val title: String
    ) : ListItem() {
        override val itemType: Int
            get() = HEADER
    }

    data class Body(
        val rating: Float,
        val description: String,
    ) : ListItem() {
        override val itemType: Int
            get() = BODY
    }

    data class ViewPager(
        val fragmentManager: FragmentManager,
        val lifecycle: Lifecycle
    ) : ListItem() {
        override val itemType: Int
            get() = VIEWPAGER
    }

    companion object {
        const val HEADER = 1
        const val BODY = 2
        const val VIEWPAGER = 3
    }
}
