package com.example.products.api

object ApiUtils {

    private const val BASE_URL = "https://gorest.co.in/public-api/"

    val apiService : ApiService?
    get() = RetrofitClient.getClient(BASE_URL)?.create(ApiService::class.java)

}