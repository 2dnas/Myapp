package com.example.products.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName


data class Product(
        val meta : ProductMeta?,
        val data : List<ProductData>?
)


data class ProductData(
        var id : Int?,
        val name : String?,
        val description : String?,
        val image : String?,
        val price : Double?,
        @SerializedName("discount_amount")
        val discountAmount : Double?,
        val status : Boolean?,
        val categories : List<ProductDataCategories>
)


data class ProductDataCategories(
        var id : Int? = 0,
        val name : String? = "",
)


data class ProductMeta(
        val pagination : ProductPaging
)


data class ProductPaging(
        val total : Int?,
        val page : Int?
)