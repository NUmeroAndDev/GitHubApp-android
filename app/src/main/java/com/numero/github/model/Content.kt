package com.numero.github.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Content(
        val name: String,
        val path: String,
        val url: String,
        val type: String)