package com.numero.github.api

import com.numero.github.model.Auth
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GithubApi {
    @FormUrlEncoded
    @POST("/authorizations")
    fun login(@Field("note") note:String, @Body id:String, @Body password:String):Observable<Auth>
}