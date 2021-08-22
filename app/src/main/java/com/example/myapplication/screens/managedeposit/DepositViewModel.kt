package com.example.myapplication.screens.managedeposit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.History
import com.example.myapplication.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DepositViewModel : ViewModel() {
    private val depositDatabase = FirebaseDatabase.getInstance().getReference("rutnap")
    private val userDatabase = FirebaseDatabase.getInstance().getReference("user")
    private val incomeDatabase = FirebaseDatabase.getInstance().getReference("income")

    private val mDepositData = MutableLiveData<MutableList<Deposit>>()
    private val userLiveData = MutableLiveData<User?>()

    fun setDataForRecyclerView() = viewModelScope.launch {
        depositDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val mDeposit = mutableListOf<Deposit>()
                for (value in snapshot.children) {
                    val deposit = value.getValue(Deposit::class.java)
                    if (!deposit!!.status) {
                        mDeposit.add(deposit!!)
                    }
                }
                mDepositData.postValue(mDeposit)
                Log.d(TAG, "onDataChange: ${mDeposit.size} ")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun checkToUpdateMoneyForUser(deposit: Deposit) = viewModelScope.launch {
        val money = deposit?.money.replace(",", "").replace(" đồng", "").trim()
        if (deposit.isRut) {
            userDatabase.child(deposit.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        val moneyUpdate = user?.totalMoney!!.toInt() - money.toInt()
                        val userNameHashMap: HashMap<String, String> = HashMap<String, String>()
                        userNameHashMap["totalMoney"] = moneyUpdate.toString()
                        userDatabase.child(deposit.uid)
                            .updateChildren(userNameHashMap as Map<String, Any>)
                            .addOnSuccessListener {
                            }.addOnFailureListener {
                                Log.d("kienda", "updateTheUserPackage: + ${it.message}")
                            }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
            return@launch
        }
        userDatabase.child(deposit.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                val moneyUpdate = user?.totalMoney!!.toInt() + money.toInt()
                val userNameHashMap: HashMap<String, String> = HashMap<String, String>()
                userNameHashMap["totalMoney"] = moneyUpdate.toString()
                userDatabase.child(deposit.uid).updateChildren(userNameHashMap as Map<String, Any>)
                    .addOnSuccessListener {
                    }.addOnFailureListener {
                        Log.d("kienda", "updateTheUserPackage: + ${it.message}")
                    }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    fun checkToUpdateRutNapDatabase(deposit: Deposit) = viewModelScope.launch {
        depositDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listValue = mutableListOf<Deposit>()
                for (value in snapshot.children) {
                    val currentValue = value.getValue(Deposit::class.java)

                    if (currentValue == deposit) {
                        if (currentValue.isRut) {
                            val userNameHashMap: HashMap<String, Boolean> =
                                HashMap<String, Boolean>()
                            userNameHashMap["status"] = true
                            depositDatabase.child(deposit.uid + "rut")
                                .updateChildren(userNameHashMap as Map<String, Any>)
                                .addOnSuccessListener {
                                }.addOnFailureListener {
                                    Log.d(TAG, "updateTheUserPackage: + ${it.message}")
                                }
                            return
                        }
                        val userNameHashMap: HashMap<String, Boolean> =
                            HashMap<String, Boolean>()
                        userNameHashMap["status"] = true
                        depositDatabase.child(deposit.uid + "nap")
                            .updateChildren(userNameHashMap as Map<String, Any>)
                            .addOnSuccessListener {
                            }.addOnFailureListener {
                                Log.d(TAG, "updateTheUserPackage: + ${it.message}")
                            }
                    }
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun checkToUpdateTheIncomeDatabase(deposit: Deposit) = viewModelScope.launch {
        deposit.status = true
        incomeDatabase.child(deposit.uid).push().setValue(deposit)

    }

    fun getDataOfTheUser(deposit: Deposit) = viewModelScope.launch {
        userDatabase.child(deposit.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                userLiveData.postValue(user)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getDepositData(): LiveData<MutableList<Deposit>> {
        return mDepositData
    }

    fun getUserDatabase(): LiveData<User?> {
        return userLiveData
    }

    companion object {
        private const val TAG = "DepositViewModel"
    }


}