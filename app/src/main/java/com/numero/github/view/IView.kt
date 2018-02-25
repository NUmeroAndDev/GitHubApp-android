package com.numero.github.view

interface IView<in T> {
    fun setPresenter(presenter: T)
}