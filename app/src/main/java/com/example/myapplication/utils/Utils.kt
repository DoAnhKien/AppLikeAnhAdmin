package com.example.myapplication.utils

import com.google.firebase.auth.FirebaseAuth

object Utils {
   fun getUID(): String {
        return FirebaseAuth.getInstance().currentUser?.uid?:""
    }
}