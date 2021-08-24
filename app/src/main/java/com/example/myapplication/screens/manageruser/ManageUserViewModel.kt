package com.example.myapplication.screens.manageruser

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

class ManageUserViewModel : ViewModel() {

    private val depositDatabase = FirebaseDatabase.getInstance().getReference("rutnap")
    private val userDatabase = FirebaseDatabase.getInstance().getReference("user")
    private val incomeDatabase = FirebaseDatabase.getInstance().getReference("income")
    var money = ObservableField("")
    private val userDatabaseLiveData = MutableLiveData<MutableList<User>>()


    fun checkToGetAllTheUserOnFirebase() = viewModelScope.launch {
        userDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val mUser = mutableListOf<User>()
                for (value in snapshot.children) {
                    val currentUser = value.getValue(User::class.java)
                    mUser.add(currentUser!!)
                }
                userDatabaseLiveData.postValue(mUser)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    fun updateTheMoneyForUser(user: User?, money: Long) = viewModelScope.launch {
        val deposit = Deposit(
            user!!.uid,
            money.toString(),
            "0",
            System.currentTimeMillis().toString(),
            true,
            status = true
        )
        depositDatabase.child(user.uid).push().setValue(deposit)
        userDatabase.child(user!!.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var currentUser = snapshot.getValue(User::class.java)
                val currentMoney = currentUser?.totalMoney!!.toLong() + money
                currentUser.totalMoney = currentMoney.toString()

                var userNameHashMap: HashMap<String, String> = HashMap<String, String>()
                userNameHashMap["totalMoney"] = currentMoney.toString()
                userDatabase.child(user.uid).updateChildren(userNameHashMap as Map<String, Any>)
                    .addOnSuccessListener {
                    }.addOnFailureListener {
                        Log.d("kienda", "updateTheUserPackage: + ${it.message}")
                    }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun unLockForUser(user: User) = viewModelScope.launch {
        user.status = true
        userDatabase.child(user.uid).setValue(user)
    }


    fun getUserLiveData(): LiveData<MutableList<User>> {
        return userDatabaseLiveData
    }


}