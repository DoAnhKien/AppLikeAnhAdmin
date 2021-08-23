package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Deposit(
    var uid: String,
    var money: String,
    var packages: String,
    var time: String,
    var isRut: Boolean,
    var status: Boolean,
): Parcelable {
    constructor() : this(
        "",
        "0",
        "0", "0", false, false
    )
}