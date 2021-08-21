package com.example.myapplication.model

data class Admin(
    val userName: String,
    var userPassword: String,
) {
    constructor() : this(
        "",
        "",
    )
}