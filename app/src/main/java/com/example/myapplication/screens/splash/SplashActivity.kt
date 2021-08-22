package com.example.myapplication.screens.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.base.SharedPref
import com.example.myapplication.screens.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Đã login thì vào
        if (SharedPref.getInstance(this).getBoolean("isLogin")){
            startActivity(Intent(this, MainActivity::class.java))
            return
        }
        startActivity(Intent(this, LoginActivity::class.java) )
    }

    override fun onBackPressed() {

    }
}