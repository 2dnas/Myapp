package com.example.products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.products.adapter.ProductsAdapter
import com.example.products.databinding.ActivityMainBinding
import com.example.products.db.App
import com.example.products.db.DbProduct
import com.example.products.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var PAGE = 0;
    private var productViewModel: ProductViewModel? = null
    private var productsAdapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        initRecyclerView()


        if (binding.swipeRefresh.isRefreshing) {
            Toast.makeText(this, "Wait for Refresh", Toast.LENGTH_SHORT).show()
        } else {

            binding.swipeRefresh.setOnRefreshListener {

                productViewModel?.getProduct()?.observe(this, { it ->
                    var categories = ""
                    it.data?.forEach { it.categories.forEach { categories += ", ${it.name}" } }
                    it.data?.forEach {
                        App.instance.db.getProductsDao().insert(
                            DbProduct(
                                it.id,
                                it.name,
                                it.description,
                                it.image,
                                it.price,
                                it.discountAmount,
                                it.status,
                                categories
                            )
                        )
                    }
                    productsAdapter.setData(App.instance.db.getProductsDao().getAllProduct())
                    binding.productTotal.text =
                        "Product Left : ${it.meta?.pagination?.total.toString()}"
                    binding.swipeRefresh.isRefreshing = false
                })
            }


        }


        if (App.instance.db.getProductsDao().getProductCount() == 0) {
            productViewModel?.getProduct()?.observe(this, { it ->
                var categories = ""
                it.data?.forEach { it.categories.forEach { categories += " , ${it.name}" } }
                it.data?.forEach {
                    App.instance.db.getProductsDao().insert(
                        DbProduct(
                            it.id,
                            it.name,
                            it.description,
                            it.image,
                            it.price,
                            it.discountAmount,
                            it.status,
                            categories
                        )
                    )
                }

                productsAdapter.setData(App.instance.db.getProductsDao().getAllProduct())

            })
        } else {
            productsAdapter.setData(App.instance.db.getProductsDao().getAllProduct())
        }

    }


    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = productsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if(isLastVisible()){
                        PAGE++
                        initList(PAGE)
                    }
                }
            })
            layoutManager = LinearLayoutManager(this@MainActivity)
            productsAdapter.setOnClickListener {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java).apply {
                    putExtra("Id", it)
                }
                startActivity(intent)
            }

        }
    }


    private fun initDatabase() {
        productViewModel?.getProduct()?.observe(this, { it ->
            var categories = ""
            it.data?.forEach { it.categories.forEach { categories += " , ${it.name}" } }
            it.data?.forEach {
                App.instance.db.getProductsDao().insert(
                    DbProduct(
                        it.id,
                        it.name,
                        it.description,
                        it.image,
                        it.price,
                        it.discountAmount,
                        it.status,
                        categories
                    )
                )
            }
        })
    }

    fun initList(page : Int){
        productViewModel?.getProductByPage(page)?.observe(this,{
            var categories = ""
            it.data?.forEach { it.categories.forEach { categories += " , ${it.name}" } }
            it.data?.forEach { it ->
                App.instance.db.getProductsDao().insert(
                    DbProduct(
                        it.id,
                        it.name,
                        it.description,
                        it.image,
                        it.price,
                        it.discountAmount,
                        it.status,
                        categories
                    )
                )
            }
            productsAdapter.setData(App.instance.db.getProductsDao().getAllProduct())

        })
    }

    fun isLastVisible(): Boolean {
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        val totalItemCount = layoutManager.itemCount
        val lastVisible: Int = layoutManager.findLastVisibleItemPosition()
        val endHasReached : Boolean = lastVisible + 5 >= totalItemCount
        return totalItemCount > 0 && endHasReached
    }


}