package com.numero.github.presenter

import com.numero.github.contract.LoginContract
import com.numero.github.repository.IGithubRepository
import com.numero.github.repository.IUserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class LoginPresenter(private val view: LoginContract.View,
                     private val githubRepository: IGithubRepository,
                     private val userRepository: IUserRepository) : LoginContract.Presenter {

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
        // TODO バリデーションチェック
        view.showProgress()
        disposable = githubRepository.login(id, password)
                .flatMap {
                    userRepository.token = it.token
                    githubRepository.getUser(it.token)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userRepository.name = it.userName
                    view.hideProgress()
                    view.successLogin()
                }, {
                    view.hideProgress()
                    it.printStackTrace()
                })
    }
}