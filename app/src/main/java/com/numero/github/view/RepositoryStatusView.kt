package com.numero.github.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.numero.github.R
import kotlinx.android.synthetic.main.view_repository_status.view.*

class RepositoryStatusView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    var unwatchCount: Int = 0
        set(value) {
            field = value
            unwatchTextView.text = value.toString()
        }

    var starCount: Int = 0
        set(value) {
            field = value
            starTextView.text = value.toString()
        }

    var forkCount = 0
        set(value) {
            field = value
            forkTextView.text = value.toString()
        }

    init {
        View.inflate(context, R.layout.view_repository_status, this)
    }
}