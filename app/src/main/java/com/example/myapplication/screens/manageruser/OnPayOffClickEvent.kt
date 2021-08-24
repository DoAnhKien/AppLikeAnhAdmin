package com.example.myapplication.screens.manageruser

import com.example.myapplication.model.User

interface OnPayOffClickEvent {
    fun onPayOffClick(value: String, user: User)
}