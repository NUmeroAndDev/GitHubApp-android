package com.numero.github.presenter

import android.util.Base64
import com.numero.github.BuildConfig
import com.numero.github.api.GithubApi
import com.numero.github.api.request.AuthParams
import com.numero.github.contract.LoginContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class LoginPresenter(private val view: LoginContract.View, private val githubApi: GithubApi) : LoginContract.Presenter {

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
        val params = AuthParams(BuildConfig.APPLICATION_ID + "_Android_${System.currentTimeMillis()}")
        val authorization = "Basic " + Base64.encodeToString((id + ":" + password).toByteArray(), Base64.NO_WRAP)
        disposable = githubApi.login(params, authorization)
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