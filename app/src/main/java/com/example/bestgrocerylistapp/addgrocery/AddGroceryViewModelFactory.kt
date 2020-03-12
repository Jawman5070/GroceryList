package com.example.bestgrocerylistapp.addgrocery

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bestgrocerylistapp.database.GroceryDatabaseDao

class AddGroceryViewModelFactory (private val groceryId: Long,
                                  private val dataSource: GroceryDatabaseDao, private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddGroceryViewModel::class.java)){
            return AddGroceryViewModel(groceryId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}