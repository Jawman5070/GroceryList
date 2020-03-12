package com.example.bestgrocerylistapp

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.bestgrocerylistapp.database.Grocery
import com.example.bestgrocerylistapp.database.GroceryList

@BindingAdapter("groceryIdFormatted")
fun TextView.setGroceryId(grocery: Grocery?){
    grocery?.let {
        text = "ID: ${grocery.id}"
    }
}

@BindingAdapter("groceryNameFormatted")
fun TextView.setGroceryName(grocery: Grocery?){
    grocery?.let {
        text = "Name: ${grocery.name}"
    }
}

@BindingAdapter("groceryListNameFormatted")
fun TextView.setGroceryListName(groceryList: GroceryList?){
    groceryList?.let {
        text = "Name: ${groceryList.groceryName}"
    }
}

@BindingAdapter("groceryListIdFormatted")
fun TextView.setGroceryListId(groceryList: GroceryList?){
    groceryList?.let {
        text = "ID: ${groceryList.groceryListId}"
    }
}

@BindingAdapter("groceryListQuantityFormatted")
fun TextView.setGroceryListQuantity(groceryList: GroceryList?){
    groceryList?.let {
        text = "Quantity: ${groceryList.quantity}"
    }
}