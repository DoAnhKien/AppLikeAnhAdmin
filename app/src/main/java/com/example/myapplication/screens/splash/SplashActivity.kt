package com.example.myapplication.screens.splash

import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivitySplashBinding

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

    }
}