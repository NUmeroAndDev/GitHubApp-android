package com.numero.github.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.contract.RepositoryListContract
import com.numero.github.model.Repository

class RepositoryListFragment : Fragment(), RepositoryListContract.View {

    private lateinit var presenter: RepositoryListContract.Presenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    private fun initViews() {
        
    }

    companion object {
        fun newInstance(): RepositoryListFragment = RepositoryListFragment()
    }
}