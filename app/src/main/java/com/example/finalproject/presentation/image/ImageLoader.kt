package com.example.finalproject.presentation.image

import android.widget.ImageView

interface ImageLoader {
    fun load(imageView: ImageView, url: String)
}