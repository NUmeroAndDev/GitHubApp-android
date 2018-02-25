package com.numero.github.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.numero.github.R
import com.numero.github.fragment.LoginFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        showLoginFragment()
    }

    private fun showLoginFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) as? LoginFragment ?:
        LoginFragment.newInstance().also {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it).commit()
        }
    }
}