package com.example.bestgrocerylistapp.grocerylist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bestgrocerylistapp.database.GroceryDatabaseDao

class GroceryListViewModelFactory (private val dataSource: GroceryDatabaseDao,
                                   private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryListViewModel::class.java)){
            return GroceryListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}