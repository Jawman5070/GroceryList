package com.example.bestgrocerylistapp.grocerylist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bestgrocerylistapp.database.Grocery
import com.example.bestgrocerylistapp.database.GroceryDatabaseDao
import com.example.bestgrocerylistapp.database.GroceryList
import kotlinx.coroutines.*

class GroceryListViewModel(private val database: GroceryDatabaseDao,
                           application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Get livedata from database
    val groceryList = database.getGroceryList()

    // For navigation to UpdateGroceryFragment
    private val _navigateToUpdateGrocery = MutableLiveData<Long>()
    val navigateToUpdateGrocery
        get() = _navigateToUpdateGrocery
    fun onGroceryItemClicked(groceryListId: Long) {
        _navigateToUpdateGrocery.value = groceryListId
    }
    fun onUpdateGroceryNavigated() {
        _navigateToUpdateGrocery.value = null
    }

    fun onClickClearList(){
        clearGroceryList()
    }

    // Cancel all jobs on cleared
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /*************START COROUTINES **********/
    // Coroutine to get an item in GroceryList by ID
    private fun getGroceryListItemById(groceryListId: Long){
        uiScope.launch {
            getGroceryListItemFromDatabase(groceryListId)
        }
    }
    private suspend fun getGroceryListItemFromDatabase(groceryListId: Long): GroceryList {
        return withContext(Dispatchers.IO) {
            val groceryList =  database.getGroceryListById(groceryListId)
            groceryList
        }
    }

    //Coroutine to clear GroceryList in Database
    private fun clearGroceryList() {
        uiScope.launch {
            clearGroceryListInDatabase()
        }
    }
    private suspend fun clearGroceryListInDatabase() {
        withContext(Dispatchers.IO) {
            database.clearAllGroceryLists()
        }
    }
    /*************END COROUTINES **********/
}