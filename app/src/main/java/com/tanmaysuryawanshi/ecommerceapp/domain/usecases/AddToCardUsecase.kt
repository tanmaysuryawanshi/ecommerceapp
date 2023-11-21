package com.tanmaysuryawanshi.ecommerceapp.domain.usecases

import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductResponse
import com.tanmaysuryawanshi.ecommerceapp.domain.repository.ProductInfoRepository
import com.tanmaysuryawanshi.ecommerceapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToCardUsecase @Inject constructor(private val respository: ProductInfoRepository) {
    operator fun invoke(
        productName: String,
        productType: String,
        price: String,
        tax: String
    ): Flow<Resource<ProductResponse>> {
        return respository.addProduct(
            productName,
            productType,
            price,
            tax
        );
    }
}