package com.example.products.api

import com.example.products.model.Product
import com.example.products.model.ProductByIdModel
import com.example.products.model.ProductData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    fun getProducts() : Call<Product>

    @GET("products/{id}")
    fun getProductById(@Path("id")id : Int) : Call<ProductByIdModel>

    @GET("products")
    fun getProductsByPage(@Query("page")page : Int) : Call<Product>

}