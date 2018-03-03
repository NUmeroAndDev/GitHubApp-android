package com.numero.github.contract

import com.numero.github.model.Event
import com.numero.github.model.Repository
import com.numero.github.presenter.IPresenter
import com.numero.github.view.IView

interface ReceivedEventListContract {

    interface View : IView<Presenter> {
        fun showEventList(eventList: List<Event>)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : IPresenter
}