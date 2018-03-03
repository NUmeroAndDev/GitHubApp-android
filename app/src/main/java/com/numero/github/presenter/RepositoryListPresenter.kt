package com.numero.github.presenter

import com.numero.github.contract.RepositoryListContract
import com.numero.github.repository.IGithubRepository
import com.numero.github.repository.IUserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class RepositoryListPresenter(private val view: RepositoryListContract.View,
                              private val githubRepository: IGithubRepository,
                              private val userRepository: IUserRepository) : RepositoryListContract.Presenter {

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
        disposable = githubRepository.getRepositories(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgress()
                    view.showRepositoryList(it)
                }, {
                    view.hideProgress()
                    it.printStackTrace()
                })
    }
}