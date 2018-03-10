package com.numero.github.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.numero.github.R
import com.numero.github.extension.component
import com.numero.github.extension.replaceFragment
import com.numero.github.fragment.ContentListFragment
import com.numero.github.fragment.RepositoryDetailFragment
import com.numero.github.model.Content
import com.numero.github.model.Repository
import com.numero.github.presenter.ContentListPresenter
import com.numero.github.repository.GithubRepository
import com.numero.github.repository.UserRepository
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class RepositoryActivity : AppCompatActivity(),
        ContentListFragment.ContentListFragmentListener {

    @Inject
    lateinit var githubRepository: GithubRepository
    @Inject
    lateinit var userRepository: UserRepository

    private val repository: Repository by lazy { intent.getSerializableExtra(INTENT_REPOSITORY) as Repository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = repository.name
        }

        component?.inject(this)

        showRepositoryDetailFragment(repository)
//        showContentListFragment(repository.name)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickContent(content: Content) {
        val fragment = ContentListFragment.newInstance(repository.name, content).also {
            replaceFragment(R.id.container, it, true)
        }
        ContentListPresenter(fragment, githubRepository, userRepository)
    }

    private fun showContentListFragment(repositoryName: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) as? ContentListFragment
                ?: ContentListFragment.newInstance(repositoryName).also {
                    replaceFragment(R.id.container, it, false)
                }
        ContentListPresenter(fragment, githubRepository, userRepository)
    }

    private fun showRepositoryDetailFragment(repository: Repository) {
        if (supportFragmentManager.findFragmentById(R.id.container) !is RepositoryDetailFragment) {
            RepositoryDetailFragment.newInstance(repository).also {
                replaceFragment(R.id.container, it, false)
            }
        }
    }

    companion object {

        private const val INTENT_REPOSITORY = "INTENT_REPOSITORY"

        fun createIntent(context: Context, repository: Repository): Intent = Intent(context, RepositoryActivity::class.java).apply {
            putExtra(INTENT_REPOSITORY, repository)
        }
    }
}
