package com.example.myapplication.screens.userdeposit

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemDepositBinding
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.databinding.ItemUserDepositBinding
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.History
import com.example.myapplication.model.User


class UserDepositAdapter(
    var items: List<Deposit>, private val onClick: OnClickItemUserDeposit
) : RecyclerView.Adapter<UserDepositAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemUserDepositBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(var binding: ItemUserDepositBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binData(deposit: Deposit, position: Int) {
            binding.apply {
                binding.deposit = deposit
                executePendingBindings()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(newItems: List<Deposit>) {
        this.items = newItems
        notifyDataSetChanged()
    }


}