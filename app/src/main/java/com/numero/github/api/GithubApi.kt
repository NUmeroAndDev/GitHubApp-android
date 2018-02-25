package com.numero.github.api

import com.numero.github.api.request.AuthParams
import com.numero.github.model.Auth
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GithubApi {
    @POST("/authorizations")
    fun login(@Body params: AuthParams, @Header("authorization") authorization: String): Observable<Auth>
}