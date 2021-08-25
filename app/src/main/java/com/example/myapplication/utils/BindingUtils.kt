package com.example.myapplication.utils

import android.annotation.SuppressLint
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.Notification
import com.example.myapplication.model.User
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


    @BindingAdapter("setUserNameForUser")
    @JvmStatic
    fun setUserNameForUser(tv: TextView, user: User) {
        tv.text = "Họ và tên: ${user.name}"
    }

    @BindingAdapter("setUserBankForUser")
    @JvmStatic
    fun setUserBankForUser(tv: TextView, user: User) {
        tv.text = "Tên ngân hàng: ${user.bank}"
    }

    @BindingAdapter("setUserMoneyForUser")
    @JvmStatic
    fun setUserMoneyForUser(tv: TextView, user: User) {
        tv.text = "Số tiền trong ngân hàng: ${user.totalMoney}"
    }

    @BindingAdapter("setImageForIncomeType")
    @JvmStatic
    fun incomeType(img: ImageView, deposit: Deposit) {
        if (deposit.isRut) {
            Glide.with(img).load(R.drawable.ic_plus).into(img)
            return
        }
        Glide.with(img).load(R.drawable.ic_minus).into(img)
    }


    @SuppressLint("ResourceAsColor")
    @BindingAdapter("setTextForIncomeType")
    @JvmStatic
    fun incomeTypeB(tv: TextView, deposit: Deposit) {
        val fm = DecimalFormat("#,###")
        if (deposit.isRut) {
            tv.text = "Bạn đã rút - ${fm.format(deposit?.money.toLong())} VNĐ"
            tv.setTextColor(R.color.nap)
            return
        }
        tv.text = "Bạn đã nạp + ${fm.format(deposit?.money.toLong())} VNĐ"
        tv.setTextColor(R.color.rut)

    }

    @BindingAdapter("setCBForNotification")
    @JvmStatic
    fun cbForNotification(checkBox: CheckBox?, notification: Notification?) {
//        if (notification!!.status) {
//            checkBox?.isSelected = true
//            return
//        }
//        checkBox?.isSelected = false
    }

    @BindingAdapter("setTextForNotification")
    @JvmStatic
    fun textForNotification(tv: TextView?, notification: Notification?) {

        if (notification?.status!!) {
            tv?.text = "True"
            return
        }
        tv?.text = "False"


    }


}