package com.example.bestgrocerylistapp.grocerylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bestgrocerylistapp.database.GroceryList
import com.example.bestgrocerylistapp.databinding.GroceryListItemBinding

class GroceryListAdapter(private val clickListener: GroceryListListener)  : ListAdapter<GroceryList, GroceryListAdapter.GroceryListViewHolder>(GroceryListDiffCallback()) {

    class GroceryListViewHolder constructor(private val binding: GroceryListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(groceryList: GroceryList, clickListener: GroceryListListener) {
            binding.groceryList = groceryList
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): GroceryListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GroceryListItemBinding.inflate(layoutInflater, parent, false)
                return GroceryListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryListViewHolder {
        return GroceryListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GroceryListViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class GroceryListDiffCallback: DiffUtil.ItemCallback<GroceryList>(){
        override fun areItemsTheSame(oldItem: GroceryList, newItem: GroceryList): Boolean {
            return oldItem.groceryListId == newItem.groceryListId
        }

        override fun areContentsTheSame(oldItem: GroceryList, newItem: GroceryList): Boolean {
            return oldItem == newItem
        }
    }
}

// Listener for button clicks on RecyclerView
class GroceryListListener(val clickListener: (groceryListId: Long) -> Unit) {
    fun onClick(groceryList: GroceryList) = clickListener(groceryList.groceryListId)
}



