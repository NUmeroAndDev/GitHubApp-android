package com.numero.github.repository

import com.numero.github.model.Auth
import io.reactivex.Observable

interface IGithubRepository {
    fun login(id: String, password: String): Observable<Auth>
}