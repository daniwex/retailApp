package com.adenikinju.productapplicationviewskotlin.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adenikinju.productapplicationviewskotlin.R
import com.adenikinju.productapplicationviewskotlin.data.Product

class DiscoverProductsRecyclerViewAdapter(
    private val context: Context,
    private val items: List<Product>
): RecyclerView.Adapter<DiscoverProductsRecyclerViewAdapter.DiscoverProductViewHolder>() {

    inner class DiscoverProductViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView){
        val productImage: ImageView = itemView.findViewById(R.id.ivRecommendedProductImage)
        val productLiked: ImageView = itemView.findViewById(R.id.isLiked)
        val productCategory: TextView = itemView.findViewById(R.id.productCategory)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productOldPrice: TextView = itemView.findViewById(R.id.productPriceOne)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiscoverProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product, parent, false)
        return DiscoverProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DiscoverProductViewHolder,
        position: Int
    ) {
        val currentItem = items[position]
        with(holder) {
            productImage.setImageResource(currentItem.productImage)
            productDescription.text = currentItem.productDescription
            productCategory.text = currentItem.productCategory
            if(currentItem.productPriceOne != null){
                productOldPrice.text = currentItem.productPriceOne.toString()
                productOldPrice.paintFlags = productPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                productPrice.setTextColor(context.getColor(R.color.green_750))
            }
            productPrice.text = "$${currentItem.productPrice}"
        }
    }

    override fun getItemCount() = items.size
}