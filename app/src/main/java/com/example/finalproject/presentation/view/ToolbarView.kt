package com.example.finalproject.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.ViewToolbarBinding
import com.example.finalproject.common.setAttrs

class ToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewToolbarBinding =
        ViewToolbarBinding.inflate(LayoutInflater.from(context), this)

    init {
        setAttrs(attrs, R.styleable.ToolbarView) {
            binding.title.text = it.getString(R.styleable.ToolbarView_toolbar_title)
            binding.title.setTextColor(
                it.getColor(
                    R.styleable.ToolbarView_toolbar_title_color,
                    ContextCompat.getColor(context, R.color.white)
                )
            )

            binding.drawableStart.isVisible =
                it.getBoolean(R.styleable.ToolbarView_drawable_start_visibility, true)
            binding.drawableEnd.isVisible =
                it.getBoolean(R.styleable.ToolbarView_drawable_end_visibility, true)

            binding.drawableStart.setImageResource(
                it.getResourceId(
                    R.styleable.ToolbarView_drawable_start,
                    R.drawable.baseline_arrow_back_24
                )
            )
            binding.drawableEnd.setImageResource(
                it.getResourceId(
                    R.styleable.ToolbarView_drawable_end,
                    R.drawable.ic_search
                )
            )

            binding.drawableStart.setOnClickListener {
                onDrawableStartClick?.invoke()
            }

            binding.drawableEnd.setOnClickListener {
                onDrawableEndClick.invoke()
            }
        }
    }

    var title: String
        get() = binding.title.text.toString()
        set(value) {
            binding.title.text = value
        }

    var onDrawableStartClick: (() -> Unit)? = null

    var onDrawableEndClick: (() -> Unit) = {
        findNavController().navigate(R.id.action_global_searchFragment)
    }
}

