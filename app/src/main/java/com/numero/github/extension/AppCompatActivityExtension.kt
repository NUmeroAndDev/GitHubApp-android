package com.numero.github.extension

import android.support.v7.app.AppCompatActivity
import com.numero.github.GithubApplication
import com.numero.github.di.ApplicationComponent

val AppCompatActivity.component: ApplicationComponent?
    get() = (application as? GithubApplication)?.component