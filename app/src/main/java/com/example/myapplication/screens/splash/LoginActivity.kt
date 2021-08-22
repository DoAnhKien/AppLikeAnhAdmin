package com.example.myapplication.screens.splash

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.SharedPref
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.model.Admin
import com.example.myapplication.screens.main.MainActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : BaseActivity<ActivityLoginBinding>(), View.OnClickListener {

    private var adminDatabase = FirebaseDatabase.getInstance().getReference("admin")

    override fun initLayout(): Int = R.layout.activity_login

    override fun init() {

    }

    override fun setOnClickForViews() {
        binding?.btnDangNhap?.setOnClickListener(this)
    }

    override fun initViews() {

    }

    private fun checkForLogin() {
        val user = binding?.edtTk?.text.toString()
        val pass = binding?.edtMk?.text.toString()

        if (user.isNotEmpty()&&pass.isNotEmpty()){
            adminDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var userx=  snapshot.getValue(Admin::class.java)

                    if (userx?.username?.contains(user) == true && userx.pass.contains(pass)){
                        //Save login done
                        SharedPref.getInstance(this@LoginActivity).setBoolean("isLogin", true)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    }
                    else{
                        Toast.makeText(this@LoginActivity, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
        else{
            Toast.makeText(this, "Không được để trống!", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        private const val TAG = "LoginActivity"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDangNhap -> {
                checkForLogin()
            }
        }
    }

    override fun onBackPressed() {

    }
}