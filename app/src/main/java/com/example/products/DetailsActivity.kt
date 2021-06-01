package com.example.products

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.products.adapter.DetailsAdapter
import com.example.products.databinding.ActivityDetailsBinding
import com.example.products.viewmodel.ProductViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsBinding
    private var productViewModel : ProductViewModel? = null
    private var detailsAdapter = DetailsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val id = intent.extras?.getInt("Id")

        productViewModel?.getProductById(id!!)?.observe(this, {
            detailsAdapter.setData(it.data.categories)
            Glide.with(this)
                .load(it.data.image)
                .centerCrop()
                .into(binding.detailsImage)

            Log.d("chemiid",it.data.name.toString())

            binding.apply {
                detailsName.text = it.data.name
                detailsDescription.text = it.data.description
                detailsPrice.apply {
                        text = it.data.price.toString()
                        paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                detailsNewPrice.text = it.data.discountAmount.toString()
                if(it.data.status == true){
                    binding.detailsStatus.text = "Available"
                }else {
                    binding.detailsStatus.text = "Not Available"
                }
            }
        })




    }


    fun initRecyclerView(){
        binding.detailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailsActivity)
            adapter = detailsAdapter
        }
    }
}