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

class ProductsRecyclerViewAdapter(
    private val context: Context,
    private var products: List<Product>
) :
    RecyclerView.Adapter<ProductsRecyclerViewAdapter.HomeRecommendationViewHolder>() {

    inner class HomeRecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
    ): HomeRecommendationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product, parent, false)
        return HomeRecommendationViewHolder(view)
    }

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: HomeRecommendationViewHolder,
        position: Int
    ) {
        val currentItem = products[position]
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

    override fun getItemCount() = products.size
}