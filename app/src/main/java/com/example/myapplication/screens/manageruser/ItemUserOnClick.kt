package com.example.myapplication.screens.manageruser

import com.example.myapplication.model.User

interface ItemUserOnClick {
    fun onClick(position: Int, user: User)
    fun onLongClick(position: Int, user: User)
}