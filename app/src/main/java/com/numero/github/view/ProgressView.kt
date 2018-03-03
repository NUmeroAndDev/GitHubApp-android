package com.numero.github.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.numero.github.R

class ProgressView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_progress, this)
    }

    fun show() {
        visibility = View.VISIBLE
    }

    fun hide() {
        visibility = View.INVISIBLE
    }
}