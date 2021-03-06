package com.numero.github.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.github.R
import com.numero.github.contract.ContentListContract
import com.numero.github.model.Content
import com.numero.github.view.adapter.ContentListAdapter
import kotlinx.android.synthetic.main.fragment_content_list.*

class ContentListFragment : Fragment(), ContentListContract.View {

    private lateinit var presenter: ContentListContract.Presenter
    private var listener: ContentListFragmentListener? = null
    private val contentListAdapter: ContentListAdapter = ContentListAdapter().apply {
        setOnItemClickListener {
            listener?.onClickContent(it)
        }
    }
    private lateinit var repositoryName: String
    private var content: Content? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ContentListFragmentListener) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoryName = arguments?.getString(ARG_REPOSITORY_NAME) ?: return
        content = arguments?.getSerializable(ARG_CONTENT) as? Content
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
        val url = content?.url
        if (url != null) {
            presenter.loadContentListFromUrl(url)
        } else {
            presenter.loadContentList(repositoryName)
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }

    override fun setPresenter(presenter: ContentListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showContentList(contentList: List<Content>) {
        contentListAdapter.contentList = contentList
    }

    override fun showProgress() {
        progressView.show()
    }

    override fun hideProgress() {
        progressView.hide()
    }

    private fun initViews() {
        contentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = contentListAdapter
        }
    }

    interface ContentListFragmentListener {
        fun onClickContent(content: Content)
    }

    companion object {
        private const val ARG_REPOSITORY_NAME = "ARG_REPOSITORY_NAME"
        private const val ARG_CONTENT = "ARG_CONTENT"

        fun newInstance(repositoryName: String): ContentListFragment = newInstance(repositoryName, null)

        fun newInstance(repositoryName: String, content: Content?): ContentListFragment = ContentListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_REPOSITORY_NAME, repositoryName)
                putSerializable(ARG_CONTENT, content)
            }
        }
    }
}