package com.numero.github.presenter

import com.numero.github.contract.ContentListContract
import com.numero.github.repository.IGithubRepository
import com.numero.github.repository.IUserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class ContentListPresenter(private val view: ContentListContract.View,
                           private val githubRepository: IGithubRepository,
                           private val userRepository: IUserRepository) : ContentListContract.Presenter {

    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
        disposable?.apply {
            if (isDisposed.not()) {
                dispose()
            }
        }
    }

    override fun loadContentList(repositoryName: String) {
        view.showProgress()
        val name = userRepository.name ?: return
        disposable = githubRepository.getContents(name, repositoryName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgress()
                    view.showContentList(it)
                }, {
                    view.hideProgress()
                    it.printStackTrace()
                })
    }

    override fun loadContentListFromUrl(url: String) {
        view.showProgress()
        disposable = githubRepository.getContents(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.hideProgress()
                    view.showContentList(it)
                }, {
                    view.hideProgress()
                    it.printStackTrace()
                })
    }
}