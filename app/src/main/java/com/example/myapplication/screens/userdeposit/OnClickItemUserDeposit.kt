package com.example.myapplication.screens.userdeposit

import com.example.myapplication.model.Deposit
import com.example.myapplication.model.History
import java.text.FieldPosition

interface OnClickItemUserDeposit {
    fun onClick(position: Int, deposit: Deposit)
    fun onLongClick(position: Int, deposit: Deposit)
}