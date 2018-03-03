package com.numero.github.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.contract.RepositoryListContract
import com.numero.github.model.Repository
import com.numero.github.view.adapter.RepositoryListAdapter
import kotlinx.android.synthetic.main.fragment_repository_list.*

class RepositoryListFragment : Fragment(), RepositoryListContract.View {

    private lateinit var presenter: RepositoryListContract.Presenter
    private var listener: RepositoryListFragmentListener? = null
    private val repositoryListAdapter: RepositoryListAdapter = RepositoryListAdapter().apply {
        setOnItemClickListener {
            listener?.onClickRepository(it)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is RepositoryListFragmentListener) {
            listener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }

    override fun setPresenter(presenter: RepositoryListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showRepositoryList(repositoryList: List<Repository>) {
        repositoryListAdapter.repositoryList = repositoryList
    }

    override fun showProgress() {
        progressView.show()
    }

    override fun hideProgress() {
        progressView.hide()
    }

    private fun initViews() {
        repositoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = repositoryListAdapter
        }
    }

    interface RepositoryListFragmentListener {
        fun onClickRepository(repository: Repository)
    }

    companion object {
        fun newInstance(): RepositoryListFragment = RepositoryListFragment()
    }
}