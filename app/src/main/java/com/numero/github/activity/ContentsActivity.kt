package com.numero.github.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.numero.github.R
import com.numero.github.extension.component
import com.numero.github.fragment.ReceivedEventListFragment
import com.numero.github.fragment.RepositoryListFragment
import com.numero.github.presenter.ReceivedEventListPresenter
import com.numero.github.presenter.RepositoryListPresenter
import com.numero.github.repository.GithubRepository
import com.numero.github.repository.UserRepository
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class ContentsActivity : AppCompatActivity() {

    @Inject
    lateinit var githubRepository: GithubRepository
    @Inject
    lateinit var userRepository: UserRepository

    private val repositoryName: String by lazy { intent.getStringExtra(INTENT_REPOSITORY_NAME) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents)
        setSupportActionBar(toolbar)

        component?.inject(this)
    }

    companion object {

        private const val INTENT_REPOSITORY_NAME = "INTENT_REPOSITORY_NAME"

        fun createIntent(context: Context, repositoryName: String): Intent = Intent(context, ContentsActivity::class.java).apply {
            putExtra(INTENT_REPOSITORY_NAME, repositoryName)
        }
    }
}
