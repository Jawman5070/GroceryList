package com.example.bestgrocerylistapp.grocerystore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.bestgrocerylistapp.database.GroceryDatabaseDao
import kotlinx.coroutines.*

class GroceryStoreViewModel(val database: GroceryDatabaseDao,
                            application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val groceryItems = database.getAllGroceries()


    private val _navigateToAddGrocery = MutableLiveData<Long>()
    val navigateToAddGrocery
        get() = _navigateToAddGrocery
    fun onGroceryItemClicked(groceryId: Long) {
        _navigateToAddGrocery.value = groceryId
    }
    fun onUpdateGroceryNavigated() {
        _navigateToAddGrocery.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}