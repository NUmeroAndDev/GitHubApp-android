package com.numero.github.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.contract.SplashContract

class SplashFragment : Fragment(), SplashContract.View {

    private lateinit var presenter: SplashContract.Presenter
    private var listener: SplashFragmentListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SplashFragmentListener) {
            listener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }

    override fun setPresenter(presenter: SplashContract.Presenter) {
        this.presenter = presenter
    }

    override fun successLogin() {
        listener?.showMainScreen()
    }

    override fun failedLogin() {
        listener?.showLoginScreen()
    }

    interface SplashFragmentListener {
        fun showMainScreen()

        fun showLoginScreen()
    }

    companion object {
        fun newInstance(): SplashFragment = SplashFragment()
    }
}