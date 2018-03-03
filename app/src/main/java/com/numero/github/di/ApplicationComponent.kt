package com.numero.github.di

import com.numero.github.activity.RepositoryActivity
import com.numero.github.activity.LoginActivity
import com.numero.github.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (ApiModule::class),
    (RepositoryModule::class)
])
interface ApplicationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(repositoryActivity: RepositoryActivity)
}