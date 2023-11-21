package com.tanmaysuryawanshi.ecommerceapp.data.remote

import com.tanmaysuryawanshi.ecommerceapp.data.remote.dto.ItemDto
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductResponse
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @GET("api/public/get")
    suspend fun getProducts(): List<ItemDto>

    @Multipart
    @POST("api/public/add")
    suspend fun addProduct(
        @Part("product_name") productName: RequestBody,
        @Part("product_type") productType: RequestBody,
        @Part("price") price: RequestBody,
        @Part("tax") tax: RequestBody,

        ): ProductResponse
}