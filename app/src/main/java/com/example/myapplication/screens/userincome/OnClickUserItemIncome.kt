package com.example.myapplication.screens.userincome

import com.example.myapplication.model.Income

interface OnClickUserItemIncome {
    fun onClick(position: Int, income: Income)
    fun onLongClick(position: Int, income: Income)
}