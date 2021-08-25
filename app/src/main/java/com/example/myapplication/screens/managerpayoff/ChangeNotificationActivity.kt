package com.example.myapplication.screens.managerpayoff

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.Const
import com.example.myapplication.base.LoadingDialog
import com.example.myapplication.base.ShowDialog

import com.example.myapplication.databinding.ActivityChangeNotificationBinding
import com.example.myapplication.screens.managerhistory.ChangeAdminInformationViewModel

class ChangeNotificationActivity : BaseActivity<ActivityChangeNotificationBinding>(),
    View.OnClickListener {
    private val viewModel: ChangeNotificationViewModel by viewModels()
    private lateinit var loading: LoadingDialog
    private lateinit var dialog: ShowDialog.Builder

    override fun initLayout(): Int = R.layout.activity_change_notification

    override fun init() {

    }

    override fun setOnClickForViews() {
        binding?.tvCancelUpdateNotification?.setOnClickListener(this)
        binding?.tvUpdateNotification?.setOnClickListener(this)
        binding?.cbStatus?.setOnClickListener(this)
    }

    override fun initViews() {
        viewModel.getDataFromFirebase()
        viewModel.getNotificationLiveData().observe(this, {
            binding?.notification = it
            if (it.status) {
                binding?.cbStatus!!.isChecked = true;
                binding?.tvStatus?.text =  Const.YOU_TURNED_ON_THE_NOTIFICATION
            } else if (!it.status) {
                binding?.cbStatus!!.isSelected = false
                binding?.tvStatus?.text = Const.YOU_TURNED_OFF_THE_NOTIFICATION
            }
        })
    }

    private fun updateInformationForNotification() {
        if (binding?.edtContent?.text!!.toString().isNotEmpty()) {
            if (binding?.tvStatus!!.text!!.toString() == Const.YOU_TURNED_OFF_THE_NOTIFICATION) {
                viewModel.updateNotificationInformation(binding?.edtContent?.text.toString(), false)
                Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show()
                onBackPressed()
                return
            }
            viewModel.updateNotificationInformation(binding?.edtContent?.text.toString(), true)
            Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show()
            onBackPressed()
            return
        }
        dialog.show("Bạn cần điền đầy đủ thông tin", "")

    }

    private fun changeTheStatus() {
        if (binding?.tvStatus!!.text!!.toString() == Const.YOU_TURNED_OFF_THE_NOTIFICATION) {
            binding?.cbStatus!!.isChecked = true
            binding?.tvStatus?.text = Const.YOU_TURNED_ON_THE_NOTIFICATION
            return
        }
        binding?.cbStatus!!.isSelected = false
        binding?.tvStatus?.text = Const.YOU_TURNED_OFF_THE_NOTIFICATION
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvCancelUpdateNotification -> {
                onBackPressed()
            }
            R.id.tvUpdateNotification -> {
                updateInformationForNotification()
            }
            R.id.cbStatus -> {
                changeTheStatus()
            }
        }
    }
}