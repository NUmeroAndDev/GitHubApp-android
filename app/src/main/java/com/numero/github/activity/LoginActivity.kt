package com.numero.github.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.numero.github.R
import com.numero.github.extension.component
import com.numero.github.fragment.LoginFragment
import com.numero.github.fragment.SplashFragment
import com.numero.github.presenter.LoginPresenter
import com.numero.github.presenter.SplashPresenter
import com.numero.github.repository.GithubRepository
import com.numero.github.repository.UserRepository
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(),
        LoginFragment.LoginFragmentListener,
        SplashFragment.SplashFragmentListener {

    @Inject
    lateinit var githubRepository: GithubRepository
    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        component?.inject(this)

        if (userRepository.hasToken) {
            showSplashFragment()
        } else {
            showLoginFragment()
        }
    }

    override fun showMainScreen() {
        startActivity(MainActivity.createIntent(this))
    }

    override fun showLoginScreen() {
        showLoginFragment()
    }

    private fun showSplashFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) as? SplashFragment
                ?: SplashFragment.newInstance().also {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, it).commit()
                }
        SplashPresenter(fragment, githubRepository, userRepository)
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