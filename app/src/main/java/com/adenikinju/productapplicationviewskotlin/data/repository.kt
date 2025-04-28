package com.adenikinju.productapplicationviewskotlin.data

import com.adenikinju.productapplicationviewskotlin.R

object RetailDatabase {

    val productCategory = listOf(
        ProductCategory(
            productName = "Shirts",
            productImage = R.drawable.home
        ),
        ProductCategory(
            productName = "Outerwear",
            productImage = R.drawable.home
        ),
        ProductCategory(
            productName = "Long pants",
            productImage = R.drawable.home
        ),
        ProductCategory(
            productName = "Short Pants",
            productImage = R.drawable.home
        ),
        ProductCategory(
            productName = "Apple Tech Items",
            productImage = R.drawable.home
        ),
        ProductCategory(
            productName = "Indoor Wears",
            productImage = R.drawable.home
        ),
        ProductCategory(
            productName = "Summer Fashion",
            productImage = R.drawable.home
        ),
        ProductCategory(
            productName = "Autumn Fashion",
            productImage = R.drawable.home
        ),
    )

    val Products = listOf(
        Product(
            productCategory = "T-Shirt Men",
            productImage = R.drawable.shirtgrey,
            productDescription = "Casual T-Shirt Grey",
            productPrice = 24.00f,
            isLiked = true,
            isRecommended = true
        ),
        Product(
            productCategory = "Outwear Men",
            productImage = R.drawable.blackhoodie,
            productDescription = "Fleece Hoodie Black",
            productPrice = 68.00f,
            productPriceOne = 84.00f,
            isLiked = true,
            isRecommended = true
        ),
        Product(
            productCategory = "Outwear Men",
            productImage = R.drawable.home,
            productDescription = "Fleece Hoodie Black",
            productPrice = 68.00f,
            productPriceOne = 84.00f,
            isLiked = true,
            isRecommended = true
        ),
        Product(
            productCategory = "T-Shirt Men",
            productImage = R.drawable.home,
            productDescription = "Casual T-Shirt Grey",
            productPrice = 53.00f,
            isLiked = true
        ),
        Product(
            productCategory = "Outwear Women",
            productImage = R.drawable.hoodiepink,
            productDescription = "Casual Hoodie Pink",
            productPrice = 45.00f,
            productPriceOne = 56.00f,
            isLiked = true
        ),
        Product(
            productCategory = "Outwear Men",
            productImage = R.drawable.hoodiecream,
            productDescription = "Casual Hoodie Cream",
            productPrice = 50.00f,
            productPriceOne = 59.00f,
            isLiked = true,
            isRecommended = true
        ),
        Product(
            productCategory = "Long pants Men",
            productImage = R.drawable.greypants,
            productDescription = "Long Casual pants Grey",
            productPrice = 70.00f,
            isLiked = true
        ),
    )

    val recommendProducts = Products.filter { it.isRecommended }

    val genderItems = listOf("Men", "Women", "Unisex")
    val sizeItems = listOf("XXS", "XS", "S", "M", "L", "XL", "XXL", "XXL")
    val colorItems = listOf(
        "Black", "White", "Red", "Grey", "Yellow", "Pink"
    )

}

data class ProductCategory(val productName: String, val productImage: Int)

data class Product(
    val productCategory: String,
    val productImage: Int,
    val productDescription: String,
    val productPrice: Float,
    val productPriceOne: Float? = null,
    val isLiked: Boolean = false,
    val isRecommended: Boolean = false
)
