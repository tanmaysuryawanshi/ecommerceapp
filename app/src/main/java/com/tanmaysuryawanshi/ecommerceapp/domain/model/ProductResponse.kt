package com.tanmaysuryawanshi.ecommerceapp.domain.model

data class ProductResponse(
    val message: String,
    val product_details: Responseitem,
    val product_id: Int,
    val success: Boolean
)