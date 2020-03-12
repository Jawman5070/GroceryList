package com.example.bestgrocerylistapp.grocerystore

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bestgrocerylistapp.database.GroceryDatabaseDao
import java.lang.IllegalArgumentException

class GroceryStoreViewModelFactory(private val dataSource: GroceryDatabaseDao,
                                   private val application: Application) : ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryStoreViewModel::class.java)){
                return GroceryStoreViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}