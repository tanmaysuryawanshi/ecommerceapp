package com.tanmaysuryawanshi.ecommerceapp.domain.repository

import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductResponse
import com.tanmaysuryawanshi.ecommerceapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody


interface ProductInfoRepository {
    fun getProduct(word:String): Flow<Resource<List<ProductItem>>>

    fun addProduct(productName: String,
                   productType: String,
                   price: String,
                   tax: String): Flow<Resource<ProductResponse>>
}