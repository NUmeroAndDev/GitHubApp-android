package com.numero.github.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Repository(
        val id: Int,
        val name: String,
        @Json(name = "full_name")
        val fullName: String,
        val description: String?,
        val language: String?)