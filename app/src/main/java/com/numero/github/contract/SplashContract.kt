package com.numero.github.contract

import com.numero.github.presenter.IPresenter
import com.numero.github.view.IView

interface SplashContract {

    interface View : IView<Presenter> {
        fun successLogin()

        fun failedLogin()
    }

    interface Presenter : IPresenter
}