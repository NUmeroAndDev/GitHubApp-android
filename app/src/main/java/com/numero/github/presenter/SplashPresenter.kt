package com.numero.github.presenter

import com.numero.github.contract.SplashContract
import com.numero.github.model.User
import com.numero.github.repository.IGithubRepository
import com.numero.github.repository.IUserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class SplashPresenter(private val view: SplashContract.View,
                      private val githubRepository: IGithubRepository,
                      private val userRepository: IUserRepository) : SplashContract.Presenter {

    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        loadUser()
    }

    override fun unSubscribe() {
        disposable?.apply {
            if (isDisposed.not()) {
                dispose()
            }
        }
    }

    private fun loadUser() {
        val token = userRepository.token ?: run {
            view.failedLogin()
            return
        }
        val stream = Observable.zip(
                githubRepository.getUser(token),
                Observable.just(true).delay(1750, TimeUnit.MILLISECONDS),
                BiFunction<User, Boolean, User> { user, b -> user }
        )
        disposable = stream
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userRepository.name = it.userName
                    view.successLogin()
                }, {
                    view.failedLogin()
                    it.printStackTrace()
                })
    }
}