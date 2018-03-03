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
import com.numero.github.contract.ReceivedEventListContract
import com.numero.github.model.Event
import com.numero.github.view.adapter.ReceivedEventListAdapter
import kotlinx.android.synthetic.main.fragment_received_event_list.*

class ReceivedEventListFragment : Fragment(), ReceivedEventListContract.View {

    private lateinit var presenter: ReceivedEventListContract.Presenter
    private val receivedEventListAdapter: ReceivedEventListAdapter = ReceivedEventListAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_received_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }

    override fun setPresenter(presenter: ReceivedEventListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showEventList(eventList: List<Event>) {
        receivedEventListAdapter.receivedEventList = eventList
    }

    override fun showProgress() {
        progressView.show()
    }

    override fun hideProgress() {
        progressView.hide()
    }

    private fun initViews() {
        receivedEventRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = receivedEventListAdapter
        }
    }

    companion object {
        fun newInstance(): ReceivedEventListFragment = ReceivedEventListFragment()
    }
}