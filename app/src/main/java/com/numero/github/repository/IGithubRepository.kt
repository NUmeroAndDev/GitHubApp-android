package com.numero.github.repository

import com.numero.github.model.*
import io.reactivex.Observable

interface IGithubRepository {
    fun login(id: String, password: String): Observable<Auth>

    fun getUser(token: String): Observable<User>

    fun getRepositories(userName: String): Observable<List<Repository>>

    fun getReceivedEvents(userName: String): Observable<List<Event>>

    fun getReadme(userName: String, repositoryName: String): Observable<Readme>

    fun getContents(userName: String, repositoryName: String): Observable<List<Content>>

    fun getContents(url: String): Observable<List<Content>>
}