package com.example.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.products.model.Product
import com.example.products.repository.Repository

class ProductViewModel : ViewModel() {

    fun getProduct() : LiveData<Product> {
        return Repository.getProducts()
    }

}