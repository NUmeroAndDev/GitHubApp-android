package com.numero.github.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.model.Event
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_event.*

class ReceivedEventListAdapter : RecyclerView.Adapter<ReceivedEventListAdapter.ReceivedEventViewHolder>() {

    var receivedEventList: List<Event>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceivedEventViewHolder {
        return ReceivedEventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_event, parent, false))
    }

    override fun getItemCount(): Int {
        return receivedEventList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ReceivedEventViewHolder, position: Int) {
        val list = receivedEventList ?: return
        holder.apply {
            setEvent(list[position])
        }
    }

    class ReceivedEventViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun setEvent(event: Event) {
            repoNameTextView.text = event.repo.name
        }
    }
}