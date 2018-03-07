package com.numero.github.contract

import com.numero.github.model.Content
import com.numero.github.presenter.IPresenter
import com.numero.github.view.IView

interface ContentListContract {

    interface View : IView<Presenter> {
        fun showContentList(contentList: List<Content>)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : IPresenter {
        fun loadContentList(repositoryName: String)

        fun loadContentListFromUrl(url: String)
    }
}