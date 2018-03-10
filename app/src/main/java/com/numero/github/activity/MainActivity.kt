package com.numero.github.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.numero.github.R
import com.numero.github.extension.component
import com.numero.github.fragment.ReceivedEventListFragment
import com.numero.github.fragment.RepositoryListFragment
import com.numero.github.model.Repository
import com.numero.github.presenter.ReceivedEventListPresenter
import com.numero.github.presenter.RepositoryListPresenter
import com.numero.github.repository.GithubRepository
import com.numero.github.repository.UserRepository
import com.numero.github.view.NavHeaderView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        RepositoryListFragment.RepositoryListFragmentListener {

    @Inject
    lateinit var githubRepository: GithubRepository
    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        component?.inject(this)

        initViews()
        showReceivedEventFragment()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.isChecked) {
            return true
        }
        when (item.itemId) {
            R.id.nav_home -> {
                showReceivedEventFragment()
            }
            R.id.nav_repositories -> {
                showRepositoryListFragment()
            }
            R.id.nav_stars -> {
            }
            R.id.nav_following_followers -> {
            }
            R.id.nav_settings -> {
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClickRepository(repository: Repository) {
        startActivity(RepositoryActivity.createIntent(this, repository))
    }

    private fun initViews() {
        val navHeaderView = NavHeaderView(this).apply {
            setUser(githubRepository.user)
        }
        navigationView.addHeaderView(navHeaderView)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun showRepositoryListFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) as? RepositoryListFragment
                ?: RepositoryListFragment.newInstance().also {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, it).commit()
                }
        RepositoryListPresenter(fragment, githubRepository, userRepository)
    }

    private fun showReceivedEventFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) as? ReceivedEventListFragment
                ?: ReceivedEventListFragment.newInstance().also {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, it).commit()
                }
        ReceivedEventListPresenter(fragment, githubRepository, userRepository)
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
