package com.example.myapplication.screens.userincome

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemDepositBinding
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.databinding.ItemUserDepositBinding
import com.example.myapplication.databinding.ItemUserIncomeBinding
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.History
import com.example.myapplication.model.Income
import com.example.myapplication.model.User


class UserIncomeAdapter(
    var items: List<Income>, private val onClick: OnClickUserItemIncome
) : RecyclerView.Adapter<UserIncomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemUserIncomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position], position)
        holder.itemView.setOnClickListener {
            onClick.onClick(position, items[position])
        }
        holder.itemView.setOnLongClickListener {
            onClick.onLongClick(position, items[position])
            true
        }
    }

    inner class ViewHolder(var binding: ItemUserIncomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binData(income: Income, position: Int) {
            binding.apply {
                binding.income = income
                executePendingBindings()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(newItems: List<Income>) {
        this.items = newItems
        notifyDataSetChanged()
    }


}