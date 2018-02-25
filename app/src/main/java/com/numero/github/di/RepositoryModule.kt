package com.numero.github.di

import com.numero.github.api.GithubApi
import com.numero.github.repository.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(githubApi: GithubApi): GithubRepository = GithubRepository(githubApi)
}