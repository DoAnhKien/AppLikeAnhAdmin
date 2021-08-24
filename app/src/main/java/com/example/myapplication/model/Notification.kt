package com.example.myapplication.model

data class Notification(
    val mess: String,
    val status: Boolean,
) {
    constructor() : this(
        "",
        false
    )
}