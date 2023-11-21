package com.tanmaysuryawanshi.ecommerceapp.presentation.screens.AddToCard


import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductResponse
import com.tanmaysuryawanshi.ecommerceapp.domain.model.Responseitem

data class CartInfoState(
    val cartResponse:ProductResponse = ProductResponse("",
        Responseitem("","","",""),0,success = false),
    val isLoading:Boolean=false
)
