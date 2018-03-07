package com.numero.github.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.model.Content
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_content.*

class ContentListAdapter : RecyclerView.Adapter<ContentListAdapter.ContentViewHolder>() {

    private var listener: ((Content) -> Unit)? = null

    var contentList: List<Content> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setOnItemClickListener(listener: ((Content) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_content, parent, false))
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.apply {
            val content = contentList[position]
            setContent(content)
            itemView.setOnClickListener {
                if (content.isDir()) {
                    listener?.invoke(content)
                }
            }
        }
    }

    class ContentViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setContent(content: Content) {
            nameTextView.text = content.name
        }
    }
}