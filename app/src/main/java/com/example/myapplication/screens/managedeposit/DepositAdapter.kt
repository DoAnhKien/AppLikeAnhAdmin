package com.example.myapplication.screens.managedeposit

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemDepositBinding
import com.example.myapplication.model.Deposit


class DepositAdapter(
    var items: List<Deposit>, private val onClick: ItemDepositOnClick
) : RecyclerView.Adapter<DepositAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemDepositBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(var binding: ItemDepositBinding) :
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