package com.numero.github.api

import com.numero.github.api.request.AuthParams
import com.numero.github.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface GithubApi {
    @POST("/authorizations")
    fun login(@Body params: AuthParams, @Header("authorization") authorization: String): Observable<Auth>

    @GET("/user")
    fun getUser(@Query("access_token") token: String): Observable<User>

    @GET("/users/{user}/repos")
    fun getRepositories(@Path("user") user: String): Observable<List<Repository>>

    @GET("/users/{user}/received_events")
    fun getReceivedEvents(@Path("user") user: String): Observable<List<Event>>

    @GET("/repos/{user}/{repository}/contents")
    fun getContents(@Path("user") user: String, @Path("repository") repository: String): Observable<List<Content>>

    @GET
    fun getContents(@Url url: String): Observable<List<Content>>
}