package com.example.myapplication.model

data class Admin(
    val username: String,
    var pass: String,
    var bank: String,
    var stk: String,
    var name: String,
) {
    constructor() : this(
        "","", "", "", ""
    )
}