package com.example.myapplication.screens.managedeposit

import android.app.Dialog
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.*
import com.example.myapplication.databinding.ActivityManageDepositBinding
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_manage_deposit.*
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class ManageDepositActivity : BaseActivity<ActivityManageDepositBinding>(), ItemDepositOnClick {

    private val viewModel: DepositViewModel by viewModels()
    private var depositAdapter: DepositAdapter? = null
    private val list = arrayListOf<Deposit>()
    private lateinit var loading: LoadingDialog
    private lateinit var dialog: ShowDialog.Builder
    private val userDatabase = FirebaseDatabase.getInstance().getReference("user")

    override fun initLayout(): Int = R.layout.activity_manage_deposit

    override fun init() {
        initData()
        initTheRecyclerView()
        setDataForRecyclerViews()

    }

    override fun setOnClickForViews() {

    }

    override fun initViews() {

    }


    private fun initData() {
        depositAdapter = DepositAdapter(list, this)
        loading = LoadingDialog.getInstance(this)
        dialog = ShowDialog.Builder(this)
    }

    private fun setDataForRecyclerViews() {
        viewModel.getDepositData().observe(this, {
            Log.d(TAG, "setDataForRecyclerViews: ${it.size}")
            depositAdapter?.setNewData(it)
        })
    }

    private fun initTheRecyclerView() {
        viewModel.setDataForRecyclerView()
        binding.apply {
            rcvDeposit.apply {
                adapter = depositAdapter
                layoutManager =
                    LinearLayoutManager(
                        this@ManageDepositActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                hasFixedSize()
            }
        }
    }

    companion object {
        private const val TAG = "ManageDepositActivity"
    }

    override fun onClick(position: Int, deposit: Deposit) {
        viewModel.getDataOfTheUser(deposit)
        initUserData(deposit)
    }

    private fun initUserData(deposit: Deposit) {
        userDatabase.child(deposit.uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                showDialog(user, deposit)
                return
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun showDialog(user: User?, deposit: Deposit) {
        var dialog: Dialog? = null
        if (deposit.isRut) {
            dialog = ShowDialog.Builder(this)
                .title("Yêu cầu rút")
                .message(
                    "Họ và tên: ${user?.name} \n Số tiền rút - ${deposit.money} \n Thời gian: ${
                        convertTime(
                            deposit.time.toLong()
                        )
                    } \n Ngân hàng: ${user?.bank} \n Số tài khoản: ${user?.stk} "
                )
                .setRightButton("Bỏ qua", object : DialogRightInterface {
                    override fun onClick() {
                        dialog?.dismiss()
                    }
                })
                .setLeftButton("Xác nhận", object : DialogLeftInterface {
                    override fun onClick() {
                        viewModel.checkToUpdateMoneyForUser(deposit)
                        viewModel.checkToUpdateRutNapDatabase(deposit)
                        viewModel.checkToUpdateTheIncomeDatabase(deposit)
                        dialog?.dismiss()
                    }
                })
                .confirm()
            dialog?.show()
            return
        }
        dialog = ShowDialog.Builder(this)
            .title("Yêu cầu nạp")
            .message(
                "Họ và tên: ${user?.name} \n Số tiền nạp + ${deposit.money} \n Thời gian: ${
                    convertTime(
                        deposit.time.toLong()
                    )
                } \n Ngân hàng: ${user?.bank} \n Số tài khoản: ${user?.stk} "
            )
            .setRightButton("Bỏ qua", object : DialogRightInterface {
                override fun onClick() {
                    dialog?.dismiss()
                }
            })
            .setLeftButton("Xác nhận", object : DialogLeftInterface {
                override fun onClick() {
                    viewModel.checkToUpdateMoneyForUser(deposit)
                    viewModel.checkToUpdateRutNapDatabase(deposit)
                    viewModel.checkToUpdateTheIncomeDatabase(deposit)
                    viewModel.checkToUpdateUserPackage(user!!, deposit)
                    dialog?.dismiss()
                }
            })
            .confirm()
        dialog?.show()
        return


    }

    private fun convertTime(time: Long): String {
        val date = Date(time)
        val format: Format = SimpleDateFormat("dd-M-yyyy hh:mm:ss")
        return format.format(date)
    }


    override fun onLongClick(position: Int, deposit: Deposit) {

    }
}