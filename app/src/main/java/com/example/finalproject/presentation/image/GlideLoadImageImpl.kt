package com.example.finalproject.presentation.image

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.finalproject.R

class GlideLoadImageImpl : ImageLoader {
    override fun load(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(
                ColorDrawable(ContextCompat.getColor(imageView.context, R.color.bottom_navigation_color))
            )
            .override(imageView.width, imageView.height)
            .into(imageView)
    }

}