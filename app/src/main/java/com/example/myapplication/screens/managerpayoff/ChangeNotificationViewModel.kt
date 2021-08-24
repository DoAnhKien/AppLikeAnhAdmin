package com.example.myapplication.screens.managerpayoff

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class ChangeNotificationViewModel : ViewModel() {

    private val depositDatabase = FirebaseDatabase.getInstance().getReference("rutnap")
    private val userDatabase = FirebaseDatabase.getInstance().getReference("user")
    private val incomeDatabase = FirebaseDatabase.getInstance().getReference("income")
    private val notificationDatabase = FirebaseDatabase.getInstance().getReference("noti")
    var money = ObservableField("")
    private val userDatabaseLiveData = MutableLiveData<MutableList<User>>()


    private fun getDataFromFirebase() = viewModelScope.launch {

    }


}