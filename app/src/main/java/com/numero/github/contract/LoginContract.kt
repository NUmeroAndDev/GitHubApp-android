package com.numero.github.contract

import com.numero.github.presenter.IPresenter
import com.numero.github.view.IView

interface LoginContract {

    interface View : IView<Presenter> {
        fun successLogin()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : IPresenter {
        fun login(id: String, password: String)
    }
}