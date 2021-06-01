package com.example.products.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.products.model.ProductDataCategories

@Entity(tableName = "PRODUCTS")
data class DbProduct(
    @PrimaryKey
    val id : Int?,
    val name : String?,
    val description : String?,
    val image : String?,
    val oldPrice : Double?,
    val newPrice : Double?,
    val status : Boolean?,
    val category : String,
)