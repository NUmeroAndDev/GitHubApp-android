package com.numero.github.di

import android.content.Context
import com.numero.github.api.GithubApi
import com.numero.github.repository.GithubRepository
import com.numero.github.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(githubApi: GithubApi): GithubRepository = GithubRepository(githubApi)

    @Provides
    @Singleton
    fun provideUserRepository(context: Context): UserRepository = UserRepository(context)
}