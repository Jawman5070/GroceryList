package com.example.bestgrocerylistapp.updategrocery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bestgrocerylistapp.database.GroceryDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class UpdateGroceryViewModel(val groceryListId: Long, val database: GroceryDatabaseDao,
                             application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


}