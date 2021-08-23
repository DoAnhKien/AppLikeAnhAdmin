package com.example.myapplication.screens.manageruser

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.Const
import com.example.myapplication.databinding.BottomSheetLayoutBinding
import com.example.myapplication.model.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {

    private var binding: BottomSheetLayoutBinding? = null
    private lateinit var onBottomSheetClick: OnBottomSheetClick
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        setOnClickForView()
        return binding?.root
    }

    private fun setOnClickForView() {
        binding?.tvHistoryDeposit?.setOnClickListener(this)
        binding?.tvInCome?.setOnClickListener(this)
        binding?.tvUnLockUser?.setOnClickListener(this)
        binding?.tvPayOff?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvHistoryDeposit -> {
                onBottomSheetClick.onClick(Const.VIEW_HISTORY_DEPOSIT_USER, user)
                dismiss()
            }
            R.id.tvInCome -> {
                onBottomSheetClick.onClick(Const.VIEW_INCOME_USER, user)
                dismiss()
            }
            R.id.tvUnLockUser -> {
                onBottomSheetClick.onClick(Const.VIEW_UNLOCK_USER, user)
                dismiss()

            }
            R.id.tvPayOff -> {
                onBottomSheetClick.onClick(Const.VIEW_PAY_OFF_USER, user)
                dismiss()
            }
        }
    }

    fun setUser(user: User?) {
        this.user = user
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onBottomSheetClick = context as OnBottomSheetClick
        } catch (e: Exception) {
            Log.d(TAG, "onAttach: ${e.message}")
        }

    }

    companion object {
        private const val TAG = "MyBottomSheetDialog"
    }
}