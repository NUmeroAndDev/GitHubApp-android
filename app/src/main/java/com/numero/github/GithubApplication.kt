package com.numero.github

import android.app.Application
import com.facebook.soloader.SoLoader
import com.numero.github.di.*

class GithubApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .apiModule(ApiModule())
                .repositoryModule(RepositoryModule())
                .build()
    }
}