package com.example.myapplication.screens.managerpayoff

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.Notification
import com.example.myapplication.model.User
import com.example.myapplication.screens.managerhistory.ChangeAdminInformationViewModel
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
    private val notificationLiveData = MutableLiveData<Notification>()


    fun getDataFromFirebase() = viewModelScope.launch {
        notificationDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentNotification = snapshot.getValue(Notification::class.java)
                notificationLiveData.postValue(currentNotification!!)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("KienDA", "onCancelled: ${error.message}")
            }

        })
    }

    fun updateNotificationInformation(mess: String, status: Boolean) =
        viewModelScope.launch {
            var userNameHashMap: HashMap<String, String> = HashMap<String, String>()
            var userNameHashMapB: HashMap<String, Boolean> = HashMap<String, Boolean>()
            userNameHashMap["mess"] = mess
            userNameHashMapB["status"] = status
            notificationDatabase.updateChildren(userNameHashMap as Map<String, Any>)
                .addOnSuccessListener {

                }.addOnFailureListener {

                }
            notificationDatabase.updateChildren(userNameHashMapB as Map<String, Any>)
                .addOnSuccessListener {

                }.addOnFailureListener {

                }
        }

    fun getNotificationLiveData(): LiveData<Notification> {
        return notificationLiveData
    }


}