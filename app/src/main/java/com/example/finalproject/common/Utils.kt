package com.example.finalproject.common

import android.content.res.Resources
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

inline fun View.setAttrs(
    attrs: AttributeSet?,
    styleable: IntArray,
    crossinline body: (TypedArray) -> Unit
) {
    context.theme.obtainStyledAttributes(attrs, styleable, 0, 0)
        .apply {
            try {
                body.invoke(this)
            } finally {
                recycle()
            }
        }
}