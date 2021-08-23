package com.example.myapplication.screens.manageruser

import com.example.myapplication.model.User

interface OnBottomSheetClick {
    fun onClick(value: String, user: User?)
}