package com.example.myapplication.screens.splash

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.model.Admin
import com.example.myapplication.screens.main.MainActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SplashActivity : BaseActivity<ActivitySplashBinding>(), View.OnClickListener {

    private var adminDatabase = FirebaseDatabase.getInstance().getReference("admin")

    override fun initLayout(): Int = R.layout.activity_splash

    override fun init() {

    }

    override fun setOnClickForViews() {
        binding?.btnDangNhap?.setOnClickListener(this)
    }

    override fun initViews() {

    }

    private fun checkForLogin() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
//        Toast.makeText(this, "mmmm", Toast.LENGTH_SHORT).show()
//        adminDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val admin =
//                    Admin(binding?.edtTk?.text.toString(), binding?.edtMk?.text.toString())
//                for (data in snapshot.children) {
//                    val currentAdmin = data.getValue(Admin::class.java)
//                    if (currentAdmin == admin) {
//                        Log.d(TAG, "123: ${currentAdmin?.userName} ")
//
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
    }

    companion object {
        private const val TAG = "SplashActivity"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDangNhap -> {
                checkForLogin()
            }
        }
    }
}