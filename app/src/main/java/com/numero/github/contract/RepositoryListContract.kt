package com.numero.github.contract

import com.numero.github.model.Repository
import com.numero.github.presenter.IPresenter
import com.numero.github.view.IView

interface RepositoryListContract {

    interface View : IView<Presenter> {
        fun showRepositoryList(repositoryList: List<Repository>)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : IPresenter
}