package com.numero.github.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.numero.github.R
import com.numero.github.extension.component
import com.numero.github.extension.replaceFragment
import com.numero.github.fragment.ContentListFragment
import com.numero.github.presenter.ContentListPresenter
import com.numero.github.repository.GithubRepository
import com.numero.github.repository.UserRepository
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class RepositoryActivity : AppCompatActivity() {

    @Inject
    lateinit var githubRepository: GithubRepository
    @Inject
    lateinit var userRepository: UserRepository

    private val repositoryName: String by lazy { intent.getStringExtra(INTENT_REPOSITORY_NAME) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        setSupportActionBar(toolbar)

        component?.inject(this)

        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.container) as? ContentListFragment
                    ?: return@addOnBackStackChangedListener
            ContentListPresenter(fragment, githubRepository, userRepository)
        }

        showContentListFragment(repositoryName)
    }

    private fun showContentListFragment(repositoryName: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) as? ContentListFragment
                ?: ContentListFragment.newInstance(repositoryName).also {
                    replaceFragment(R.id.container, it, false)
                }
        ContentListPresenter(fragment, githubRepository, userRepository)
    }

    companion object {

        private const val INTENT_REPOSITORY_NAME = "INTENT_REPOSITORY_NAME"

        fun createIntent(context: Context, repositoryName: String): Intent = Intent(context, RepositoryActivity::class.java).apply {
            putExtra(INTENT_REPOSITORY_NAME, repositoryName)
        }
    }
}
