package com.example.products.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbProduct::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()  {
    abstract fun getProductsDao() : ProductsDao

}