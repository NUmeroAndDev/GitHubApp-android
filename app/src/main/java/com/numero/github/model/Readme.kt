package com.numero.github.model

import android.util.Base64
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Readme(
        val name: String,
        val path: String,
        val type: String,
        val content: String) {

    val contentString: String = String(Base64.decode(content.toByteArray(), Base64.DEFAULT))

}