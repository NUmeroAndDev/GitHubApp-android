package com.numero.github.model

import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class Content(
        val name: String,
        val path: String,
        val url: String,
        val type: String) : Serializable {

    fun isDir(): Boolean {
        return type == "dir"
    }
}