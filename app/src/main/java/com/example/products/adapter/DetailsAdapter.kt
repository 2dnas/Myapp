package com.example.products.adapter

import android.telecom.Call
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.products.databinding.DetailsListItemBinding
import com.example.products.model.ProductDataCategories

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    var dataset = listOf<ProductDataCategories>()

    inner class DetailsViewHolder(val binding: DetailsListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(category : String){
            binding.detailsCategory.text = category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(DetailsListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val item = dataset
        holder.bind(item[position].name!!)
    }

    override fun getItemCount() = dataset.size

    fun setData(list : List<ProductDataCategories>){
        dataset = list
        notifyDataSetChanged()
    }
}
