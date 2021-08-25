package com.example.myapplication.screens.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.SharedPref
import com.example.myapplication.databinding.ActivitySplashBinding

import com.example.myapplication.screens.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initLayout(): Int = R.layout.activity_splash

    override fun init() {
        checkForLogin()
    }

    override fun setOnClickForViews() {

    }

    override fun initViews() {

    }

    private fun checkForLogin() {
        if (SharedPref.getInstance(this).getBoolean("isLogin")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
        startActivity(Intent(this, LoginActivity::class.java))
    }


}