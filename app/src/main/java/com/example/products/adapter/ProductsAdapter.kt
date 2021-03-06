package com.example.products.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.products.databinding.ListItemBinding
import com.example.products.db.DbProduct
import com.example.products.model.Product

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var dataset = mutableListOf<DbProduct>()



    inner class ProductViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(product: DbProduct){
            binding.root.setOnClickListener {
                listener?.invoke(product.id!!)
            }
            Glide.with(itemView)
                .load(product.image)
                .centerCrop()
                .into(binding.productImage)

            binding.productName.text = product.name
            binding.productOldPrice.apply {
                text = product.oldPrice.toString()
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            binding.productNewPrice.text = product.newPrice.toString()

            if(product.status == true){
                binding.productStatus.text = "Avaibale"
            }else{
                binding.productStatus.text = "Not \n Available"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataset.size

    fun setData(list: MutableList<DbProduct>?){
        dataset = list!!
        notifyDataSetChanged()

    }

    var listener : ((id : Int) -> Unit)? = null

    fun setOnClickListener(listener: (id : Int) -> Unit) {
        this.listener = listener
    }

    fun updateList(list : MutableList<DbProduct>){
        dataset.addAll(list)
    }

}
