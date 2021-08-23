package com.example.myapplication.screens.manageruser

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemDepositBinding
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.model.Deposit
import com.example.myapplication.model.User


class ManageUserAdapter(
    var items: List<User>, private val onClick: ItemUserOnClick
) : RecyclerView.Adapter<ManageUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binData(user: User, position: Int) {
            binding.apply {
                binding.user = user
                executePendingBindings()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(newItems: List<User>) {
        this.items = newItems
        notifyDataSetChanged()
    }


}