package com.numero.github.api.request

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class AuthParams(val note: String)