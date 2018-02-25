package com.numero.github.presenter

import com.numero.github.contract.LoginContract
import io.reactivex.disposables.Disposable

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
        disposable?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }

    override fun login(id: String, password: String) {

    }
}