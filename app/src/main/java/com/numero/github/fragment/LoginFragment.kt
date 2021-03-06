package com.numero.github.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.contract.LoginContract
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter
    private var listener: LoginFragmentListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is LoginFragmentListener) {
            listener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }

    override fun successLogin() {
        listener?.showMainScreen()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    private fun initViews() {
        loginButton.setOnClickListener {
            val id = idInputEditText.text.toString()
            val password = passwordInputEditText.text.toString()
            presenter.login(id, password)
        }
    }

    interface LoginFragmentListener {
        fun showMainScreen()
    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
}