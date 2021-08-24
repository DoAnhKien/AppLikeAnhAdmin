package com.example.myapplication.screens.manageruser

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.Const
import com.example.myapplication.base.LoadingDialog
import com.example.myapplication.base.ShowDialog
import com.example.myapplication.databinding.ActivityManageUserBinding
import com.example.myapplication.databinding.DialogPayoffBinding
import com.example.myapplication.model.User
import com.example.myapplication.screens.userdeposit.UserDepositActivity
import com.example.myapplication.screens.userincome.UserIncomeActivity
import kotlinx.android.synthetic.main.activity_manage_deposit.*
import kotlinx.android.synthetic.main.activity_manage_user.*


import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import android.view.WindowManager


class ManageUserActivity : BaseActivity<ActivityManageUserBinding>(), ItemUserOnClick,
    OnBottomSheetClick, OnPayOffClickEvent {


    private val viewModel: ManageUserViewModel by viewModels()
    private var manageUserAdapter: ManageUserAdapter? = null
    private val list = arrayListOf<User>()
    private lateinit var loading: LoadingDialog
    private lateinit var mDialog: ShowDialog.Builder
    private lateinit var dialogBinding: DialogPayoffBinding

    override fun initLayout(): Int = R.layout.activity_manage_user

    override fun init() {
        initRecyclerViews()
    }

    override fun setOnClickForViews() {

    }

    override fun initViews() {

    }

    private fun initRecyclerViews() {
        loading = LoadingDialog.getInstance(this)
        mDialog = ShowDialog.Builder(this)
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
                showPayOffBottomSheet(user)
            }
            Const.VIEW_UNLOCK_USER -> {
                viewModel.unLockForUser(user!!)
                mDialog.show("Mở khóa thành công", "Bạn đã mở khóa cho tài khoản ${user.name}")
            }
            Const.VIEW_INCOME_USER -> {
                moveToUserIncomeActivity(user)
            }
            Const.VIEW_HISTORY_DEPOSIT_USER -> {
                moveToUserDepositActivity(user)
            }
            Const.VIEW_PHONE_CALL -> {
                makeThePhoneCall(user)
            }
        }
    }

    private fun makeThePhoneCall(user: User?) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", user?.phone, null))
        startActivity(intent)
    }

    private fun showPayOffBottomSheet(user: User?) {
        val bottomSheet = BottomSheetPayOff()
        bottomSheet.show(supportFragmentManager, "MyBottomSheetA")
        bottomSheet.setUser(user)
    }

    private fun moveToUserDepositActivity(user: User?) {
        val intent = Intent(this, UserDepositActivity::class.java).apply {
            putExtra("123", user)
        }
        startActivity(intent)
    }


    private fun moveToUserIncomeActivity(user: User?) {
        val intent = Intent(this, UserIncomeActivity::class.java).apply {
            putExtra("123", user)
        }
        startActivity(intent)
    }


    private fun showDialogForBonusOrPunish(value: String, user: User?) {
        val dialog = Dialog(this)
        val binding: DialogPayoffBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(this),
                R.layout.dialog_payoff,
                null,
                false
            )
        dialog.apply {
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        dialog.setContentView(binding.root)

        when (value) {
            Const.VIEW_PUNISH -> {
                binding.tvDDD.text = "Bạn phạt người dùng ${user?.name}"
            }
            Const.VIEW_BONUS -> {
                binding.tvDDD.text = "Bạn thưởng người dùng ${user?.name}"
            }
        }


        binding.tvConfirmPayOff.setOnClickListener {
            when (value) {
                Const.VIEW_PUNISH -> {
                    if (binding.edtMoney.text!!.isNotEmpty()) {
                        viewModel.updateTheMoneyForUser(
                            user,
                            -binding.edtMoney.text.toString().toLong()
                        )
                        dialog.dismiss()
                        return@setOnClickListener
                    }
                    mDialog.show("Không được bỏ khoản tiền", "")

                }
                Const.VIEW_BONUS -> {
                    if (binding.edtMoney.text!!.isNotEmpty()) {
                        viewModel.updateTheMoneyForUser(
                            user,
                            binding.edtMoney.text.toString().toLong()
                        )
                        dialog.dismiss()
                        return@setOnClickListener
                    }
                    mDialog.show("Không được bỏ khoản tiền", "")
                }
            }
        }

        binding.tvCancelPayOff.setOnClickListener {
            dialog.dismiss()
        }



        dialog.show()

    }

    override fun onPayOffClick(value: String, user: User) {
        when (value) {
            Const.VIEW_BONUS -> {
                showDialogForBonusOrPunish(value, user)
            }
            Const.VIEW_PUNISH -> {
                showDialogForBonusOrPunish(value, user)
            }
        }
    }

    companion object {
        private const val TAG = "ManageUserActivity"
    }

}