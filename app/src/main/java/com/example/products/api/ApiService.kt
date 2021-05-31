package com.example.products.api

import com.example.products.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    fun getProducts() : Call<Product>

}