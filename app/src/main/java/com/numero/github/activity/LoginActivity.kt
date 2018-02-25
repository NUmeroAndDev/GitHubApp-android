package com.numero.github.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.numero.github.GithubApplication
import com.numero.github.R
import com.numero.github.api.GithubApi
import com.numero.github.fragment.LoginFragment
import com.numero.github.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var githubApi: GithubApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        application?.apply {
            if (this is GithubApplication) {
                this.component.inject(this@LoginActivity)
            }
        }

        showLoginFragment()
    }

    private fun showLoginFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) as? LoginFragment
                ?: LoginFragment.newInstance().also {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, it).commit()
                }
        LoginPresenter(fragment, githubApi)
    }
}