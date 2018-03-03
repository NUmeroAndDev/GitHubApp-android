package com.numero.github.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Event(
        val id: Long,
        val type: String,
        val actor: Actor,
        val repo: Repo,
        @Json(name = "created_at")
        val created: String) {

    @JsonSerializable
    data class Actor(
            val id: Long,
            @Json(name = "display_login")
            val name: String,
            @Json(name = "avatar_url")
            val imageUrl: String)

    @JsonSerializable
    data class Repo(
            val id: Long,
            val name: String,
            val url: String)
}