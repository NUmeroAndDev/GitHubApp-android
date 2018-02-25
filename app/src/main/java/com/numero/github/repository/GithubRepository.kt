package com.numero.github.repository

import android.util.Base64
import com.numero.github.BuildConfig
import com.numero.github.api.GithubApi
import com.numero.github.api.request.AuthParams
import com.numero.github.model.Auth
import com.numero.github.model.User
import io.reactivex.Observable

class GithubRepository(private val githubApi: GithubApi) : IGithubRepository {

    override fun login(id: String, password: String): Observable<Auth> {
        val params = AuthParams(BuildConfig.APPLICATION_ID + "_Android_${System.currentTimeMillis()}")
        val authorization = "Basic " + Base64.encodeToString((id + ":" + password).toByteArray(), Base64.NO_WRAP)
        return githubApi.login(params, authorization)
    }

    override fun getUser(token: String): Observable<User> {
        return githubApi.getUser(token)
    }
}