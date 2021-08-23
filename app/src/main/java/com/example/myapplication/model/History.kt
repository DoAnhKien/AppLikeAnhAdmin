package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class History(
    var money: String,
    var date: String,
    var isRut: Boolean,
) : Parcelable {
    constructor() : this(
        "",
        "",
        false
    )
}