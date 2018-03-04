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

    private var listener: ((Repository) -> Unit)? = null

    var repositoryList: List<Repository> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setOnItemClickListener(listener: ((Repository) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_repository, parent, false))
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.apply {
            val repository = repositoryList[position]
            setRepository(repository)
            itemView.setOnClickListener {
                listener?.invoke(repository)
            }
        }
    }

    class RepositoryViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setRepository(repository: Repository) {
            nameTextView.text = repository.name
        }
    }
}