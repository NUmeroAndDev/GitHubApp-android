package com.numero.github.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.numero.github.R
import com.numero.github.model.User
import kotlinx.android.synthetic.main.view_nav_header.view.*

class NavHeaderView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.view_nav_header, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUser(user: User) {
        nameTextView.text = user.name
        userNameTextView.text = user.userName
    }
}
