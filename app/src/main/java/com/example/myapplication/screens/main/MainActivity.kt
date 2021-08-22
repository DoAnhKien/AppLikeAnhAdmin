package com.example.myapplication.screens.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.screens.managedeposit.ManageDepositActivity
import com.example.myapplication.screens.managerhistory.ManageHistoryActivity
import com.example.myapplication.screens.managerpayoff.ManagePayOffActivity
import com.example.myapplication.screens.manageruser.ManageUserActivity

class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener {

    override fun initLayout(): Int = R.layout.activity_main

    override fun init() {
    }

    override fun setOnClickForViews() {
        binding?.llManageDeposit?.setOnClickListener(this)
        binding?.llManagePayOff?.setOnClickListener(this)
        binding?.llManageUser?.setOnClickListener(this)
        binding?.llMangeHistory?.setOnClickListener(this)
    }

    override fun initViews() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.llManageDeposit -> {
                moveToDepositActivity()
            }
            R.id.llManagePayOff -> {
                moveToPayOffActivity()
            }
            R.id.llManageUser -> {
                moveToUserActivity()
            }
            R.id.llMangeHistory -> {
                moveToHistoryActivityActivity()
            }
        }
    }

    private fun moveToDepositActivity() {
        val moveToDepositIntent = Intent(this, ManageDepositActivity::class.java)
        startActivity(moveToDepositIntent)
    }

    private fun moveToHistoryActivityActivity() {
        val moveToHistoryIntent = Intent(this, ManageHistoryActivity::class.java)
        startActivity(moveToHistoryIntent)
    }

    private fun moveToUserActivity() {
        val moveToUserActivityIntent = Intent(this, ManageUserActivity::class.java)
        startActivity(moveToUserActivityIntent)
    }

    private fun moveToPayOffActivity() {
        val moveToPayOffActivityIntent = Intent(this, ManagePayOffActivity::class.java)
        startActivity(moveToPayOffActivityIntent)
    }

}