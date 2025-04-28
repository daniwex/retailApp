package com.adenikinju.productapplicationviewskotlin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adenikinju.productapplicationviewskotlin.data.Product
import com.adenikinju.productapplicationviewskotlin.data.ProductCategory
import com.adenikinju.productapplicationviewskotlin.data.RetailDatabase

class RetailViewModel : ViewModel() {

    private val _productCategories =
        MutableLiveData<List<ProductCategory>>(RetailDatabase.productCategory)
    val productCategory: LiveData<List<ProductCategory>> = _productCategories

    private val _recommendedProducts =
        MutableLiveData<List<Product>>(RetailDatabase.recommendProducts)
    val recommendedProducts: LiveData<List<Product>> = _recommendedProducts

    private val _products =
        MutableLiveData<List<Product>>(RetailDatabase.Products)
    val products: LiveData<List<Product>> get() = _products

    private val allProducts = RetailDatabase.Products

    private var _filteredProductsText = MutableLiveData<String>("")
    val filteredProductsText:  LiveData<String> = _filteredProductsText

    fun filterElements(query: String) {
        if (query.isBlank() or query.isEmpty()) {
            _products.value = allProducts

        } else {
            val m = query.split(":")
            var aQuery = when{
                m.size > 1 -> m[1]
                else -> query
            }
            val filteredList = allProducts.filter { product ->
                product.productDescription.contains(aQuery, ignoreCase = true) ||
                        product.productCategory.contains(aQuery, ignoreCase = true)

            }
            _products.value = filteredList
        }
    }

    fun resetFiltering(){
        _products.value = allProducts
    }

    fun setFilteredText(str: String){
        _filteredProductsText.value = str
    }

}