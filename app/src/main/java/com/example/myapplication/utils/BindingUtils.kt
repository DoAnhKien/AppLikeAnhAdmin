package com.example.myapplication.utils

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Deposit
import java.lang.Exception
import java.text.DecimalFormat

object BindingUtils {

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("setTextForRutNapDeposit")
    @JvmStatic
    fun incomeType(tv: TextView, deposit: Deposit) {
        val fm = DecimalFormat("###,###")
        val money = deposit.money.replace(",", "").replace(" đồng", "").trim()
        if (deposit.isRut) {
            tv.text = "Rut - $money VND"
            tv.setTextColor(R.color.nap)
            return
        }
        tv.text = "Nạp + $money VND"
        tv.setTextColor(R.color.nap)
    }


    @SuppressLint("ResourceAsColor")
    @BindingAdapter("setTextForDeposit")
    @JvmStatic
    fun incomeTypeA(tv: TextView, deposit: Deposit) {
        tv.text = "ID người dùng ${deposit.uid}"
    }

}