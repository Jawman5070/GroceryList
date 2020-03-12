package com.example.bestgrocerylistapp.database

import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryDatabaseDao {

    //Used to prepopulate the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroceries(groceries: List<Grocery>)

    @Query("SELECT * from grocery_table order by id ASC")
    fun getAllGroceries(): LiveData<List<Grocery>>

    @Query ("SELECT * from grocery_table WHERE id = :key")
    fun getGroceryByIdLive(key: Long): LiveData<Grocery>

    @Query ("SELECT * from grocery_table WHERE id = :key")
    fun getGroceryById(key: Long): Grocery


    @Insert
    fun insertGroceryList(groceryList: GroceryList)

    @Query ("SELECT * from grocery_list_table WHERE groceryListId = :key")
    fun getGroceryListById(key: Long): GroceryList

    @Query ("SELECT * FROM grocery_list_table")
    fun getGroceryList(): LiveData<List<GroceryList>>

    @Query ("DELETE FROM grocery_list_table")
    fun clearAllGroceryLists()

    @Query ("DELETE FROM grocery_list_table WHERE groceryListId = :key")
    fun deleteGroceryList(key: Long)

}