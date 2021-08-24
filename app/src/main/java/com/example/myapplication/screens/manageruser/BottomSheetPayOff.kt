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
import com.example.myapplication.databinding.BottomSheetPayOffBinding
import com.example.myapplication.model.User
import com.example.myapplication.screens.manageruser.OnBottomSheetClick
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetPayOff : BottomSheetDialogFragment(), View.OnClickListener {
    private var binding: BottomSheetPayOffBinding? = null
    private lateinit var onPayOffClickEvent: OnPayOffClickEvent
    private var user: User? = null


    fun setUser(user: User?) {
        this.user = user
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetPayOffBinding.inflate(inflater, container, false)
        setOnClickForView()
        return binding?.root
    }

    private fun setOnClickForView() {
        binding?.tvBonus?.setOnClickListener(this)
        binding?.tvPunish?.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onPayOffClickEvent = context as OnPayOffClickEvent
        } catch (e: Exception) {
            Log.d(TAG, "onAttach: ${e.message}")
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvBonus -> {
                onPayOffClickEvent.onPayOffClick(Const.VIEW_BONUS, user!!)
                dismiss()
            }
            R.id.tvPunish -> {
                onPayOffClickEvent.onPayOffClick(Const.VIEW_PUNISH, user!!)
                dismiss()
            }
        }
    }

    companion object {
        private const val TAG = "BottomSheetPayOff"
    }

}