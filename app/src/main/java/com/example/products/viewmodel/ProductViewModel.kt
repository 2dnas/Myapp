package com.example.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.products.model.Product
import com.example.products.model.ProductByIdModel
import com.example.products.model.ProductData
import com.example.products.repository.Repository

class ProductViewModel : ViewModel() {

    fun getProduct() : LiveData<Product> {
        return Repository.getProducts()
    }

    fun getProductById(id : Int) : LiveData<ProductByIdModel>{
        return Repository.getProductById(id)
    }

    fun getProductByPage(page : Int) : LiveData<Product>{
        return Repository.getProductsByPage(page)
    }

}