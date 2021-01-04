package com.architecture.clean.ui.home.adapter

import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup

import com.architecture.clean.ui.DataBindingViewHolder
import com.ua.domain.entity.FoodEntity
import com.ua.presentation.databinding.FoodItemRowBinding

class HomeAdapter(
        private var items: ArrayList<FoodEntity> = arrayListOf<FoodEntity>()
) : androidx.recyclerview.widget.RecyclerView.Adapter<HomeAdapter.SimpleHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding  = FoodItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding)
        : DataBindingViewHolder<FoodEntity>(dataBinding)  {
        override fun onBind(t: FoodEntity): Unit = with(t) {
            println("--Not implemented yet")
        }
    }

    fun add(list: ArrayList<FoodEntity>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}