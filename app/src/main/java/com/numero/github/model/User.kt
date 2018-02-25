package com.numero.github.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * User (https://developer.github.com/v3/users/)
 *
 *
 */
@JsonSerializable
data class User(
        @Json(name = "login")
        val userName: String,
        val id: Int,
        @Json(name = "avatar_url")
        val avatarImageUrl: String,
        val name: String,
        val company: String?,
        val blog: String?,
        val location: String,
        val email: String?,
        val bio: String?,
        @Json(name = "public_repos")
        val publicRepositoryCount: Int,
        @Json(name = "followers")
        val followerCount:Int,
        @Json(name = "following")
        val followingCount:Int,
        @Json(name = "created_at")
        val created: String,
        @Json(name = "updated_at")
        val updated: String)