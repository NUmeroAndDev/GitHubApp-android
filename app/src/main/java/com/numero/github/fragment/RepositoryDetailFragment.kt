package com.numero.github.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.model.Repository
import kotlinx.android.synthetic.main.fragment_repository_detail.*

class RepositoryDetailFragment : Fragment() {

    private lateinit var repository: Repository
    private var listener: RepositoryDetailFragmentListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is RepositoryDetailFragmentListener) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = arguments?.getSerializable(ARG_REPOSITORY) as Repository
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repository_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        repositoryStatusView.apply {
            unwatchCount = repository.unwatchCount
            starCount = repository.starCount
            forkCount = repository.forkCount
        }
        viewSourceButton.setOnClickListener {
            listener?.showContent()
        }
    }

    interface RepositoryDetailFragmentListener {
        fun showContent()
    }

    companion object {
        private const val ARG_REPOSITORY = "ARG_REPOSITORY"

        fun newInstance(repository: Repository): RepositoryDetailFragment = RepositoryDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_REPOSITORY, repository)
            }
        }
    }
}