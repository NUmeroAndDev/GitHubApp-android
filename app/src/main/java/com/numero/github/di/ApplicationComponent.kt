package com.numero.github.di

import com.numero.github.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class)])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}