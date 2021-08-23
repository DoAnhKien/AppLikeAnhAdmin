package com.example.myapplication.screens.userdeposit

import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.LoadingDialog
import com.example.myapplication.base.ShowDialog
import com.example.myapplication.databinding.ActivityUserDepositBinding
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.User
import com.example.myapplication.screens.manageruser.ManageUserAdapter
import com.example.myapplication.screens.manageruser.ManageUserViewModel
import kotlinx.android.synthetic.main.activity_manage_user.*
import kotlinx.android.synthetic.main.activity_user_deposit.*

class UserDepositActivity : BaseActivity<ActivityUserDepositBinding>(), OnClickItemUserDeposit {

    private val viewModel: UserDepositViewModel by viewModels()
    private var userDepositAdapter: UserDepositAdapter? = null
    private val list = arrayListOf<Deposit>()
    private lateinit var loading: LoadingDialog
    private lateinit var dialog: ShowDialog.Builder


    override fun initLayout(): Int = R.layout.activity_user_deposit

    override fun init() {
        initRecyclerViews()
        getDataFromManageUserActivity()
    }

    override fun setOnClickForViews() {

    }

    private fun initRecyclerViews() {
        userDepositAdapter = UserDepositAdapter(list, this)
        binding.let {
            rcvUserDeposit.apply {
                adapter = userDepositAdapter
                layoutManager =
                    LinearLayoutManager(
                        this@UserDepositActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                hasFixedSize()
            }
        }

    }


    override fun initViews() {

    }

    private fun getDataFromManageUserActivity() {
        val user = intent.getParcelableExtra<User?>("123")
        viewModel.chekToGetDataFromFirebase(user)
        viewModel.getDepositDatabase().observe(this, { mDeposit ->
            userDepositAdapter?.setNewData(mDeposit)
        })
        Toast.makeText(this, "${user?.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(position: Int, deposit: Deposit) {

    }

    override fun onLongClick(position: Int, deposit: Deposit) {

    }
}