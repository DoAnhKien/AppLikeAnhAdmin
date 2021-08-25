package com.example.myapplication.model

data class Notification(
    var mess: String,
    var status: Boolean,
) {
    constructor() : this(
        "",
        false
    )
}