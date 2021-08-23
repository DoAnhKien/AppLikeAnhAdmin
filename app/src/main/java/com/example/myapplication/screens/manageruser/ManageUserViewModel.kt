package com.example.myapplication.screens.manageruser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun unLockForUser(user: User) = viewModelScope.launch {
        user.status = true
        userDatabase.child(user.uid).setValue(user)
    }


    fun getUserLiveData(): LiveData<MutableList<User>> {
        return userDatabaseLiveData
    }


}