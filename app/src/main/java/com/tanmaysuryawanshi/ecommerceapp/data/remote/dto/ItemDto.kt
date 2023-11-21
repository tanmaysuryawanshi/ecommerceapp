package com.tanmaysuryawanshi.ecommerceapp.data.remote.dto

import com.tanmaysuryawanshi.ecommerceapp.data.local.entity.ProductEntity
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem

data class ItemDto(
    val image: String,
    val price: Double,
    val product_name: String,
    val product_type: String,
    val tax: Double
)
{
    fun toProductItem():ProductItem{
        return ProductItem(image, price, product_name, product_type, tax)
    }
    fun toProductEntity():ProductEntity{
        return ProductEntity(image = image, price =  price, product_name =  product_name, product_type =  product_type, tax =  tax)
    }
}