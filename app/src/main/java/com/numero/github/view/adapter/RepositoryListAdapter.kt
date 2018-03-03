package com.numero.github.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.model.Repository
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_repository.*

class RepositoryListAdapter : RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder>() {

    var repositoryList: List<Repository>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_repository, parent, false))
    }

    override fun getItemCount(): Int {
        return repositoryList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val list = repositoryList ?: return
        holder?.apply {
            setRepository(list[position])
        }
    }

    class RepositoryViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setRepository(repository: Repository) {
            nameTextView.text = repository.name
        }
    }
}