package com.example.myapplication.model

data class History(
    var money: String,
    var date: String,
    var isRut: Boolean,
) {
    constructor() : this(
        "",
        "",
        false
    )
}