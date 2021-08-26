package com.example.myapplication.model

data class Upload(
    val imageName: String = "PhotoBackGround",
    val imageUrl: String
) {
    constructor() : this(
        "", ""
    )
}