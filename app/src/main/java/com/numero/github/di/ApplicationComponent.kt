package com.numero.github.di

import com.numero.github.activity.MainActivity
import com.numero.github.activity.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApiModule::class),
    (RepositoryModule::class)
])
interface ApplicationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
}