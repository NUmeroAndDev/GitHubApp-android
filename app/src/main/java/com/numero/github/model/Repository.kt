package com.numero.github.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Repository(
        val id: Long,
        val name: String,
        @Json(name = "full_name")
        val fullName: String,
        val description: String?,
        @Json(name = "stargazers_count")
        val starCount: Int,
        @Json(name = "watchers_count")
        val unwatchCount: Int,
        @Json(name = "forks_count")
        val forkCount: Int,
        val language: String?) : Serializable