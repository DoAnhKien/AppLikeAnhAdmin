package com.example.myapplication.screens.main

import android.content.Intent
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.screens.managedeposit.ManageDepositActivity
import com.example.myapplication.screens.managerhistory.ChangeAdminInformationActivity
import com.example.myapplication.screens.managerpayoff.ChangeNotificationActivity
import com.example.myapplication.screens.managerphoto.ManageThePhotoActivity
import com.example.myapplication.screens.manageruser.ManageUserActivity

class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener {

    override fun initLayout(): Int = R.layout.activity_main

    override fun init() {
    }

    override fun setOnClickForViews() {
        binding?.llManageDeposit?.setOnClickListener(this)
        binding?.llChangeInformationAdmin?.setOnClickListener(this)
        binding?.llManageUser?.setOnClickListener(this)
        binding?.llManageNotification?.setOnClickListener(this)
        binding?.llMangeThePhoTo?.setOnClickListener(this)
    }

    override fun initViews() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.llManageDeposit -> {
                moveToDepositActivity()
            }
            R.id.llManageNotification -> {
                moveToNotificationActivity()
            }
            R.id.llManageUser -> {
                moveToUserActivity()
            }
            R.id.llChangeInformationAdmin -> {
                moveToChangeInformationAdminActivity()
            }
            R.id.llMangeThePhoTo -> {
                moveToManagePhoToActivity()
            }
        }
    }

    private fun moveToDepositActivity() {
        val moveToDepositIntent = Intent(this, ManageDepositActivity::class.java)
        startActivity(moveToDepositIntent)
    }

    private fun moveToChangeInformationAdminActivity() {
        val moveToHistoryIntent = Intent(this, ChangeAdminInformationActivity::class.java)
        startActivity(moveToHistoryIntent)
    }

    private fun moveToUserActivity() {
        val moveToUserActivityIntent = Intent(this, ManageUserActivity::class.java)
        startActivity(moveToUserActivityIntent)
    }

    private fun moveToNotificationActivity() {
        val moveToPayOffActivityIntent = Intent(this, ChangeNotificationActivity::class.java)
        startActivity(moveToPayOffActivityIntent)
    }

    private fun moveToManagePhoToActivity() {
        val moveToManageThePhoToActivity = Intent(this, ManageThePhotoActivity::class.java)
        startActivity(moveToManageThePhoToActivity)
    }

}