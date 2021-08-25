package com.example.myapplication.screens.managerhistory

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Admin
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class ChangeAdminInformationViewModel : ViewModel() {

    private val depositDatabase = FirebaseDatabase.getInstance().getReference("rutnap")
    private val userDatabase = FirebaseDatabase.getInstance().getReference("user")
    private val adminDatabase = FirebaseDatabase.getInstance().getReference("admin")
    private val incomeDatabase = FirebaseDatabase.getInstance().getReference("income")
    private val adminDatabaseLiveData = MutableLiveData<Admin>()


    fun getDatabaseFromFirebase() = viewModelScope.launch {
        adminDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val admin = snapshot.getValue(Admin::class.java)
                adminDatabaseLiveData.postValue(admin!!)
                Log.d(TAG, "onDataChange: ${admin.name}")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun updateAdminInformation(adminName: String, adminBank: String, adminStk: String) =
        viewModelScope.launch {
            Log.d(TAG, "updateAdminInformation: 123")
            var userNameHashMap: HashMap<String, String> = HashMap<String, String>()
            userNameHashMap["name"] = adminName
            userNameHashMap["bank"] = adminBank
            userNameHashMap["stk"] = adminStk
            adminDatabase.updateChildren(userNameHashMap as Map<String, Any>)
                .addOnSuccessListener {
                    Log.d(TAG, "updateAdminInformation: succees")
                }.addOnFailureListener {
                    Log.d(TAG, "updateTheUserPackage: + ${it.message}")
                }
        }

    fun getAdminLiveData(): LiveData<Admin> {
        return adminDatabaseLiveData
    }

    companion object {
        private const val TAG = "ChangeAdminInformationViewModel"
    }


}