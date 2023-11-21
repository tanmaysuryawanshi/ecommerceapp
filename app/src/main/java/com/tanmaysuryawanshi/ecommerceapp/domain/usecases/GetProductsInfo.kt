package com.tanmaysuryawanshi.ecommerceapp.domain.usecases

import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
import com.tanmaysuryawanshi.ecommerceapp.domain.repository.ProductInfoRepository
import com.tanmaysuryawanshi.ecommerceapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetProductsInfo(private val respository:ProductInfoRepository) {

    operator fun invoke(word:String):Flow<Resource<List<ProductItem>>>{
        return respository.getProduct(word);
    }
}