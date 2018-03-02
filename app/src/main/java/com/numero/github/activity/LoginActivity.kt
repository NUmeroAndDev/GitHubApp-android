package com.numero.github.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.numero.github.GithubApplication
import com.numero.github.R
import com.numero.github.fragment.LoginFragment
import com.numero.github.presenter.LoginPresenter
import com.numero.github.repository.GithubRepository
import com.numero.github.repository.UserRepository
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginFragment.LoginFragmentListener {

    @Inject
    lateinit var githubRepository: GithubRepository
    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        application?.apply {
            if (this is GithubApplication) {
                this.component.inject(this@LoginActivity)
            }
        }

        if (userRepository.hasToken) {
            showSplashFragment()
        }else {
            showLoginFragment()
        }
    }

    override fun showMainScreen() {
        startActivity(MainActivity.createIntent(this))
    }

    private fun showSplashFragment() {
    }

    private fun showLoginFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) as? LoginFragment
                ?: LoginFragment.newInstance().also {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, it).commit()
                }
        LoginPresenter(fragment, githubRepository, userRepository)
    }
}