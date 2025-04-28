package com.adenikinju.productapplicationviewskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adenikinju.productapplicationviewskotlin.R
import com.adenikinju.productapplicationviewskotlin.data.ProductCategory

class HomeCategoryRecyclerViewAdapter(
    private val items: List<ProductCategory>,
    private val context: Context
) : RecyclerView.Adapter<HomeCategoryRecyclerViewAdapter.HomeCategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCategoryViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.category_item,parent,false)
        return HomeCategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HomeCategoryViewHolder,
        position: Int
    ) {
        val currentItem = items[position]
        with(holder){
            productText.text = currentItem.productName
            productImage.setImageResource(currentItem.productImage)
        }
    }

    override fun getItemCount() = items.size

    class HomeCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        val productText: TextView = itemView.findViewById(R.id.tvProductText)
    }
}