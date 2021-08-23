package com.example.myapplication.screens.userdeposit

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

class UserDepositViewModel : ViewModel() {

    private val incomeDatabase = FirebaseDatabase.getInstance().getReference("income")

    private val depositDatabase = MutableLiveData<MutableList<Deposit>>()

    fun chekToGetDataFromFirebase(user: User?) = viewModelScope.launch {
        incomeDatabase.child(user!!.uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listDeposit = mutableListOf<Deposit>()
                for (value in snapshot.children) {
                    val currentDeposit = value.getValue(Deposit::class.java)
                    listDeposit.add(currentDeposit!!)
                }
                depositDatabase.postValue(listDeposit)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getDepositDatabase(): LiveData<MutableList<Deposit>> {
        return depositDatabase
    }

}