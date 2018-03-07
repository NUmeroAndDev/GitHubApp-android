package com.numero.github.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.numero.github.GithubApplication
import com.numero.github.di.ApplicationComponent

val AppCompatActivity.component: ApplicationComponent?
    get() = (application as? GithubApplication)?.component

fun AppCompatActivity.replaceFragment(@IdRes res: Int, fragment: Fragment, isAddBackStack: Boolean) {
    supportFragmentManager.beginTransaction().apply {
        replace(res, fragment, fragment::class.java.simpleName)
        if (isAddBackStack) {
            addToBackStack(null)
        }
    }.commit()
}