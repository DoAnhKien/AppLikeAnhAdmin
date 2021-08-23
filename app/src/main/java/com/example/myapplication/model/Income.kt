package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Income(
    val userName: String,
    val userMoneyUp: String,
    val userDate: String,
    val userType: String
) : Parcelable {
    constructor() : this(
        "",
        "",
        "", ""
    )
}