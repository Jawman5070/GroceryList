package com.example.bestgrocerylistapp.updategrocery

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Update
import com.example.bestgrocerylistapp.database.GroceryDatabaseDao

class UpdateGroceryViewModelFactory(private val groceryListId: Long, private val dataSource: GroceryDatabaseDao,
                                    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateGroceryViewModel::class.java)){
            return UpdateGroceryViewModel(groceryListId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}