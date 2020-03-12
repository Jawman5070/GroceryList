package com.example.bestgrocerylistapp.addgrocery

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.example.bestgrocerylistapp.database.Grocery
import com.example.bestgrocerylistapp.database.GroceryDatabaseDao
import com.example.bestgrocerylistapp.database.GroceryList
import com.example.bestgrocerylistapp.grocerystore.GroceryStoreViewModel
import kotlinx.coroutines.*

class AddGroceryViewModel (private val groceryId: Long,
    dataSource: GroceryDatabaseDao, application: Application
                            ) : AndroidViewModel(application) {

    val database = dataSource
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val currentGrocery = MediatorLiveData<Grocery>()

    var groceryObject: Grocery? = null


    fun getGrocery() = currentGrocery

    init {
        currentGrocery.addSource(database.getGroceryByIdLive(groceryId), currentGrocery::setValue)
        println("$groceryId")
        getGroceryById(groceryId)
        println("$groceryObject")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun getGroceryById(groceryId: Long) {
        uiScope.launch {
            groceryObject = getGroceryItemFromDatabase(groceryId)
        }
    }

    private suspend fun getGroceryItemFromDatabase(groceryId: Long):Grocery {
        return withContext(Dispatchers.IO) {
            val grocery = database.getGroceryById(groceryId)
            grocery
        }
    }

    private fun addGroceryList(groceryList: GroceryList) {
        uiScope.launch {
            updateGroceryInDatabase(groceryList)
        }
    }

    private suspend fun updateGroceryInDatabase(groceryList: GroceryList) {
        withContext(Dispatchers.IO) {
            database.insertGroceryList(groceryList)
        }
    }

    fun onClickAddItem(groceryObject: Grocery?, quantity: String){
        val groceryList = GroceryList(groceryObject!!.id, groceryObject.name, quantity)
        addGroceryList(groceryList)
        Log.i("insertGrocery()", "Grocery Item added.")
    }
}