package com.numero.github.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Issue(
        val id: Int,
        val url: String,
        val number: Int,
        val title: String,
        val body: String)