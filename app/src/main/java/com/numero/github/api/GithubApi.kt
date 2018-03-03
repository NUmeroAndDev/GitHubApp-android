package com.numero.github.api

import com.numero.github.api.request.AuthParams
import com.numero.github.model.Auth
import com.numero.github.model.Repository
import com.numero.github.model.User
import io.reactivex.Observable
import retrofit2.http.*

interface GithubApi {
    @POST("/authorizations")
    fun login(@Body params: AuthParams, @Header("authorization") authorization: String): Observable<Auth>

    @GET("/user")
    fun getUser(@Query("access_token") token: String): Observable<User>

    @GET("/user/repos")
    fun getRepositories(@Query("access_token") token: String): Observable<List<Repository>>
}