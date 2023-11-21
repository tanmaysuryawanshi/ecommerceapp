package com.tanmaysuryawanshi.ecommerceapp.domain.model

data class ProductItem(
    val image: String,
    val price: Double,
    val product_name: String,
    val product_type: String,
    val tax: Double
)