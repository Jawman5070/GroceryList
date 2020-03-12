package com.example.bestgrocerylistapp.database

import android.content.Context
import android.telecom.Call
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Grocery::class, GroceryList::class], version = 1, exportSchema = false)
abstract class GroceryDatabase : RoomDatabase() {

    abstract val groceryDatabaseDao: GroceryDatabaseDao
    companion object{

        @Volatile
        private var INSTANCE: GroceryDatabase? = null
        //Populate the database with dummy data
        private val PREPOPULATE_DATA = listOf(Grocery(1, "Apples"), Grocery(2, "Oranges"), Grocery(3, "Bananas"), Grocery(4, "Sailor Jerry's"),
            Grocery(5, "MAGTAB"), Grocery(6, "Dan's Chicken Sauce"), Grocery(7, "Toilet Paper"), Grocery(8, "Trail Mix"))

        fun getInstance(context: Context): GroceryDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GroceryDatabase::class.java,
                        "grocery_history_database"
                    ).addCallback(object: Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                                ioThread{
                                getInstance(context).groceryDatabaseDao.insertGroceries(PREPOPULATE_DATA)
                            }
                        }
                    }).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

/*
        @Volatile
        private var INSTANCE: GroceryDatabase? = null

        fun getInstance(context: Context): GroceryDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GroceryDatabase::class.java,
                        "grocery_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
 */

