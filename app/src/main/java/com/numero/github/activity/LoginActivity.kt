package com.numero.github.activity

import android.app.Activity
import android.content.Intent
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_MAIN && resultCode == Activity.RESULT_OK) {
            finish()
        }
    }

    override fun showMainScreen() {
        startActivityForResult(MainActivity.createIntent(this), REQUEST_MAIN)
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

    companion object {
        private const val REQUEST_MAIN = 1
    }
}