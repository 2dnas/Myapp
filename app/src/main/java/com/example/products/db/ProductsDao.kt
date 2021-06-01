package com.example.products.db

import androidx.room.*
import com.example.products.model.Product


@Dao
interface ProductsDao {

    @Query("SELECT * FROM PRODUCTS")
    fun getAllProduct() : MutableList<DbProduct>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product : DbProduct)

    @Query("DELETE FROM PRODUCTS")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM PRODUCTS")
    fun getProductCount() : Int?



}