package com.example.bestgrocerylistapp.grocerystore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bestgrocerylistapp.database.Grocery
import com.example.bestgrocerylistapp.databinding.GroceryStoreItemBinding

class GroceryStoreAdapter(private val clickListener: GroceryItemListener) : ListAdapter<Grocery, GroceryStoreAdapter.GroceryViewHolder>(GroceryItemDiffCallback()) {

    // Create the ViewHolder, bind the data via databinding/LiveData provided by ViewHolder
    class GroceryViewHolder private constructor(private val binding: GroceryStoreItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(grocery: Grocery, clickListener: GroceryItemListener) {
            binding.grocery = grocery
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): GroceryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GroceryStoreItemBinding.inflate(layoutInflater, parent, false)
                return GroceryViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        return GroceryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class GroceryItemDiffCallback: DiffUtil.ItemCallback<Grocery>(){
        override fun areItemsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
            return oldItem == newItem
        }
    }
}
// Listener for button clicks on RecyclerView
class GroceryItemListener(val clickListener: (groceryId: Long) -> Unit) {
    fun onClick(grocery: Grocery) = clickListener(grocery.id)
}


