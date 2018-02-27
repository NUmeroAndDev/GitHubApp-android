package com.numero.github

import android.app.Application
import com.facebook.soloader.SoLoader
import com.numero.github.di.ApiModule
import com.numero.github.di.ApplicationComponent
import com.numero.github.di.DaggerApplicationComponent
import com.numero.github.di.RepositoryModule

class GithubApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        component = DaggerApplicationComponent.builder()
                .apiModule(ApiModule())
                .repositoryModule(RepositoryModule())
                .build()
    }
}