package com.example.bestgrocerylistapp.database

import androidx.room.*

@Entity(tableName = "grocery_list_table")
data class GroceryList(
    @PrimaryKey(autoGenerate = true)
    var groceryListId: Long = 0L,

    @ColumnInfo(name = "grocery_name")
    var groceryName: String = "",

    @ColumnInfo(name = "grocery_quantity")
    var quantity: String = ""
)





/*
@Entity(tableName = "grocery_list_table")
data class GroceryList (

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "groceryItems")
    var groceryItems: List<Grocery>

)

 */