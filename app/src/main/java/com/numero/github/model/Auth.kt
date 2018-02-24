package com.numero.github.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Auth(val id: String,
                val token: String)