package com.tanmaysuryawanshi.ecommerceapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
@Entity
data class ProductEntity(@PrimaryKey(autoGenerate = true)
                         val id: Int? = null,
                        val image: String,
                          val price: Double,
                         val product_name: String,
                          val product_type: String,
                          val tax: Double){
    fun toProductItem():ProductItem{
        return ProductItem(image, price, product_name, product_type, tax)
    }
}
