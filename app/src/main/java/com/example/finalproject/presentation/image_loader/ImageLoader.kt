package com.example.finalproject.presentation.image_loader

import android.widget.ImageView

interface ImageLoader {
    fun load(imageView: ImageView, url: String)
}