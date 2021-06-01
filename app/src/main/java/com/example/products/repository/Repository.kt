package com.example.products.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.products.api.ApiUtils
import com.example.products.model.Product
import com.example.products.model.ProductByIdModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    fun getProducts() : MutableLiveData<Product> {
        val data = MutableLiveData<Product>()

        ApiUtils.apiService?.getProducts()?.enqueue(object : Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if(response.isSuccessful && response.body() != null){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("errori", "OnFailure erori ")
            }

        })

        return data
    }

    fun getProductById(id : Int) : MutableLiveData<ProductByIdModel>{
        val data = MutableLiveData<ProductByIdModel>()

        ApiUtils.apiService?.getProductById(id)?.enqueue(object : Callback<ProductByIdModel>{
            override fun onResponse(call: Call<ProductByIdModel>, response: Response<ProductByIdModel>) {
                if(response.isSuccessful && response.body() != null){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<ProductByIdModel>, t: Throwable) {
                Log.d("errori", "OnFailure erori ")
            }

        })

        return data
    }

    fun getProductsByPage(page : Int) : MutableLiveData<Product> {
        val data = MutableLiveData<Product>()

        ApiUtils.apiService?.getProductsByPage(page)?.enqueue(object : Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if(response.isSuccessful && response.body() != null){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.d("errori", "OnFailure erori ")
            }

        })

        return data
    }

}

