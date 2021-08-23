package com.example.myapplication.screens.userincome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.Income
import com.example.myapplication.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class UserIncomeViewModel : ViewModel() {
    private val likeDatabase = FirebaseDatabase.getInstance().getReference("lịke")

    private val incomeDatabase = MutableLiveData<MutableList<Income>>()

    fun chekToGetDataFromFirebase(user: User?) = viewModelScope.launch {
        likeDatabase.child(user!!.uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listDeposit = mutableListOf<Income>()
                for (value in snapshot.children) {
                    val currentDeposit = value.getValue(Income::class.java)
                    listDeposit.add(currentDeposit!!)
                }
                incomeDatabase.postValue(listDeposit)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getIncomeDatabase(): LiveData<MutableList<Income>> {
        return incomeDatabase
    }

}