package com.example.bestgrocerylistapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_table")
data class Grocery (

    // Not auto generated because I have pre-populated the list
    @PrimaryKey
    var id: Long = 0,

    @ColumnInfo(name = "grocery_name")
    var name: String = ""

)