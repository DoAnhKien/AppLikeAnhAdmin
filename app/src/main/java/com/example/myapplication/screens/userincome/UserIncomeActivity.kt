package com.example.myapplication.screens.userincome


import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.LoadingDialog
import com.example.myapplication.base.ShowDialog
import com.example.myapplication.databinding.ActivityUserIncomeBinding
import com.example.myapplication.model.Income
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.activity_user_deposit.*
import kotlinx.android.synthetic.main.activity_user_income.*

class UserIncomeActivity : BaseActivity<ActivityUserIncomeBinding>(), OnClickUserItemIncome {

    private val viewModel: UserIncomeViewModel by viewModels()
    private var userIncomeAdapter: UserIncomeAdapter? = null
    private val list = arrayListOf<Income>()
    private lateinit var loading: LoadingDialog
    private lateinit var dialog: ShowDialog.Builder

    override fun initLayout(): Int = R.layout.activity_user_income

    override fun init() {
        initRecyclerViews()
        getDataFromManageUserActivity()
    }

    override fun setOnClickForViews() {

    }

    override fun initViews() {

    }

    private fun initRecyclerViews() {
        userIncomeAdapter = UserIncomeAdapter(list, this)
        binding.let {
            rcvIncome.apply {
                adapter = userIncomeAdapter
                layoutManager =
                    LinearLayoutManager(
                        this@UserIncomeActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                hasFixedSize()
            }
        }

    }


    private fun getDataFromManageUserActivity() {
        val user = intent.getParcelableExtra<User?>("123")
        viewModel.chekToGetDataFromFirebase(user)
        viewModel.getIncomeDatabase().observe(this, { mDeposit ->
            userIncomeAdapter?.setNewData(mDeposit)
        })
        Toast.makeText(this, "${user?.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(position: Int, income: Income) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(position: Int, income: Income) {
        TODO("Not yet implemented")
    }


}