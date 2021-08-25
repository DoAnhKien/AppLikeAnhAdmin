package com.example.myapplication.screens.managerhistory

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.LoadingDialog
import com.example.myapplication.base.ShowDialog
import com.example.myapplication.databinding.ActivityChangeAdminInformationBinding
import com.example.myapplication.screens.manageruser.ManageUserViewModel


class ChangeAdminInformationActivity : BaseActivity<ActivityChangeAdminInformationBinding>(),
    View.OnClickListener {

    private val viewModel: ChangeAdminInformationViewModel by viewModels()
    private lateinit var loading: LoadingDialog
    private lateinit var dialog: ShowDialog.Builder

    override fun initLayout(): Int = R.layout.activity_change_admin_information

    override fun init() {
        setDataForView()
    }

    override fun setOnClickForViews() {
        binding?.tvCancelInformationOfUser?.setOnClickListener(this)
        binding?.tvConfirmInformationOfUser?.setOnClickListener(this)
    }

    override fun initViews() {

    }

    private fun setDataForView() {
        loading = LoadingDialog.getInstance(this)
        dialog = ShowDialog.Builder(this)
        viewModel.getDatabaseFromFirebase()
        viewModel.getAdminLiveData().observe(this, {
            binding?.admin = it
        })

    }

    private fun checkToUpdateAdmin() {
        val adminName = binding?.edtAdminName?.text.toString()
        val adminBank = binding?.edtAdminBank?.text.toString()
        val adminStk = binding?.edtAdminStk?.text.toString()
        if (adminBank.isNotEmpty() && adminName.isNotEmpty() && adminStk.isNotEmpty()) {
            viewModel.updateAdminInformation(adminName, adminBank, adminStk)
            onBackPressed()
            Log.d(TAG, "checkToUpdateAdmin: ")
            Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show()
            return
        }
        dialog.show("Không được bỏ trống thông tin", "")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvCancelInformationOfUser -> {
                onBackPressed()
            }
            R.id.tvConfirmInformationOfUser -> {
                checkToUpdateAdmin()
            }
        }
    }

    companion object {
        private const val TAG = "ChangeAdminInformationActivity"
    }
}