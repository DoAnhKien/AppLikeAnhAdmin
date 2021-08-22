package com.example.myapplication.screens.managedeposit

import com.example.myapplication.model.Deposit

interface ItemDepositOnClick {
    fun onClick(position: Int, deposit: Deposit)
    fun onLongClick(position: Int, deposit: Deposit)
}