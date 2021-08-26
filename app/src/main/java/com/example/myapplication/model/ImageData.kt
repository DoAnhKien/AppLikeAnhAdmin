package com.example.myapplication.model

data class ImageData(
    val data : String,
    val link : String,
) {
    constructor() : this(
        "",
        "",
    )
}