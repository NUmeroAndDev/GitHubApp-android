package com.numero.github.repository

import android.util.Base64
import com.numero.github.BuildConfig
import com.numero.github.api.GithubApi
import com.numero.github.api.request.AuthParams
import com.numero.github.model.*
import io.reactivex.Observable

class GithubRepository(private val githubApi: GithubApi) : IGithubRepository {

    var user: User? = null

    override fun login(id: String, password: String): Observable<Auth> {
        val params = AuthParams(BuildConfig.APPLICATION_ID + "_Android_${System.currentTimeMillis()}")
        val authorization = "Basic " + Base64.encodeToString((id + ":" + password).toByteArray(), Base64.NO_WRAP)
        return githubApi.login(params, authorization)
    }

    override fun getUser(token: String): Observable<User> {
        if (user != null) {
            return Observable.just(user)
        }
        return githubApi.getUser(token).doOnNext { user = it }
    }

    override fun getRepositories(userName: String): Observable<List<Repository>> {
        return githubApi.getRepositories(userName)
    }

    override fun getReceivedEvents(userName: String): Observable<List<Event>> {
        return githubApi.getReceivedEvents(userName)
    }

    override fun getContents(userName: String, repositoryName: String): Observable<List<Content>> {
        return githubApi.getContents(userName, repositoryName)
    }

    override fun getContents(url: String): Observable<List<Content>> {
        return githubApi.getContents(url)
    }
}