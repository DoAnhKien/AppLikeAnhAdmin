package com.example.myapplication.screens.manageruser

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.Const
import com.example.myapplication.base.LoadingDialog
import com.example.myapplication.base.ShowDialog
import com.example.myapplication.databinding.ActivityManageUserBinding
import com.example.myapplication.model.User
import com.example.myapplication.screens.userdeposit.UserDepositActivity
import kotlinx.android.synthetic.main.activity_manage_deposit.*
import kotlinx.android.synthetic.main.activity_manage_user.*

class ManageUserActivity : BaseActivity<ActivityManageUserBinding>(), ItemUserOnClick,
    OnBottomSheetClick {


    private val viewModel: ManageUserViewModel by viewModels()
    private var manageUserAdapter: ManageUserAdapter? = null
    private val list = arrayListOf<User>()
    private lateinit var loading: LoadingDialog
    private lateinit var dialog: ShowDialog.Builder

    override fun initLayout(): Int = R.layout.activity_manage_user

    override fun init() {
        initRecyclerViews()
    }

    override fun setOnClickForViews() {

    }

    override fun initViews() {

    }

    private fun initRecyclerViews() {
        manageUserAdapter = ManageUserAdapter(list, this)
        binding.let {
            rcvUser.apply {
                adapter = manageUserAdapter
                layoutManager =
                    LinearLayoutManager(
                        this@ManageUserActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                hasFixedSize()
            }
        }
        viewModel.checkToGetAllTheUserOnFirebase()
        setDataForRecyclerViews()
    }

    private fun setDataForRecyclerViews() {
        viewModel.getUserLiveData().observe(this, { mUser ->
            manageUserAdapter?.setNewData(mUser)
        })
    }

    override fun onClick(position: Int, user: User) {
        val bottomSheet = MyBottomSheetDialog()
        bottomSheet.show(supportFragmentManager, "MyBottomSheet")
        bottomSheet.setUser(user)
    }

    override fun onLongClick(position: Int, user: User) {

    }

    override fun onClick(value: String, user: User?) {
        when (value) {
            Const.VIEW_PAY_OFF_USER -> {
                Toast.makeText(this, "${user?.name}", Toast.LENGTH_SHORT).show()
            }
            Const.VIEW_UNLOCK_USER -> {
                viewModel.unLockForUser(user!!)
                Toast.makeText(
                    this,
                    "Bạn đã mở khóa cho tài khoản ${user.name} thành công",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Const.VIEW_INCOME_USER -> {
                Toast.makeText(this, "${user?.name}", Toast.LENGTH_SHORT).show()
            }
            Const.VIEW_HISTORY_DEPOSIT_USER -> {
                moveToUserDepositActivity()

            }
        }

    }

    private fun moveToUserDepositActivity() {
        val intent = Intent(this, UserDepositActivity::class.java)
        startActivity(intent)
    }

}