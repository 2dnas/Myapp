package com.example.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.products.adapter.ProductsAdapter
import com.example.products.databinding.ActivityMainBinding
import com.example.products.db.App
import com.example.products.db.DbProduct
import com.example.products.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    private var productViewModel : ProductViewModel? = null
    private var productsAdapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        initRecyclerView()


        if(App.instance.db.getProductsDao().getProductCount() == 0){
            productViewModel?.getProduct()?.observe(this, { it ->
                var categories = ""
                it.data?.forEach { it.categories.forEach{ categories += " , ${it.name}" } }
                it.data?.forEach { App.instance.db.getProductsDao().insert(DbProduct(
                    it.id, it.name, it.description, it.image, it.price, it.discountAmount, it.status,
                    categories
                )) }
                binding.productTotal.text = "Product Left : ${it.meta?.pagination?.total.toString()}"
                productsAdapter.setData(App.instance.db.getProductsDao().getAllProduct())

            })
        }else {
            productsAdapter.setData(App.instance.db.getProductsDao().getAllProduct())
        }


//        productViewModel?.getProduct()?.observe(this, {
//            productsAdapter.setData(it)
//            binding.productTotal.text = "Product Left : ${it.meta?.pagination?.total.toString()}"
//        })
    }


    private fun initRecyclerView(){
        binding.recyclerView.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }
    }


}