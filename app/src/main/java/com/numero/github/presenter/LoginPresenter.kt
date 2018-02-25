package com.numero.github.presenter

import com.numero.github.contract.LoginContract
import com.numero.github.repository.IGithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class LoginPresenter(private val view: LoginContract.View, private val githubRepository: IGithubRepository) : LoginContract.Presenter {

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
        view.showProgress()
        disposable = githubRepository.login(id, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgress()
                    view.successLogin()
                }, {
                    view.hideProgress()
                    it.printStackTrace()
                })
    }
}