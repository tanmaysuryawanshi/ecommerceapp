package com.tanmaysuryawanshi.ecommerceapp.presentation.screens.productlist


import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem

data class ProductListInfoState(
    val productInfoItems:List<ProductItem> = emptyList(),
    val isLoading:Boolean=false
)
