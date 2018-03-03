package com.numero.github.presenter

import com.numero.github.contract.ReceivedEventListContract
import com.numero.github.repository.IGithubRepository
import com.numero.github.repository.IUserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class ReceivedEventListPresenter(private val view: ReceivedEventListContract.View,
                                 private val githubRepository: IGithubRepository,
                                 private val userRepository: IUserRepository) : ReceivedEventListContract.Presenter {

    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        loadRepositoryList()
    }

    override fun unSubscribe() {
        disposable?.apply {
            if (isDisposed.not()) {
                dispose()
            }
        }
    }

    private fun loadRepositoryList() {
        view.showProgress()
        val name = userRepository.name ?: return
        disposable = githubRepository.getReceivedEvents(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgress()
                    view.showEventList(it)
                }, {
                    view.hideProgress()
                    it.printStackTrace()
                })
    }
}