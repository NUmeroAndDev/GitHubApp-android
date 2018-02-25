package com.numero.github.repository

import com.numero.github.model.Auth
import com.numero.github.model.User
import io.reactivex.Observable

interface IGithubRepository {
    fun login(id: String, password: String): Observable<Auth>

    fun getUser(token: String): Observable<User>
}