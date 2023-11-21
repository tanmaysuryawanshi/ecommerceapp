package com.tanmaysuryawanshi.ecommerceapp.data.repository

import android.util.Log
import com.tanmaysuryawanshi.ecommerceapp.data.local.ProductDao
import com.tanmaysuryawanshi.ecommerceapp.data.remote.ApiService
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductResponse
import com.tanmaysuryawanshi.ecommerceapp.domain.repository.ProductInfoRepository
import com.tanmaysuryawanshi.ecommerceapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.IOException
import retrofit2.HttpException

class ProductRepositoryImpl(private val api:ApiService,
private val dao: ProductDao):ProductInfoRepository {
    override fun getProduct(word: String): Flow<Resource<List<ProductItem>>>
    =flow{
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toProductItem() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getProducts()
            dao.deleteWordInfos(remoteWordInfos.map { it.product_name})
            dao.insertProductInfo(remoteWordInfos.map { it.toProductEntity() })
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = wordInfos
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toProductItem() }
        emit(Resource.Success(newWordInfos))
    }

    override fun addProduct(
        productName: String,
        productType: String,
        price: String,
        tax: String
    ): Flow<Resource<ProductResponse>>  =flow{
        emit(Resource.Loading())
        val productNamePart = RequestBody.create("text/plain".toMediaTypeOrNull(), productName)
        val productTypePart = RequestBody.create("text/plain".toMediaTypeOrNull(), productType)
        val pricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), price)
        val taxPart = RequestBody.create("text/plain".toMediaTypeOrNull(), tax)

        try {
            val remoteCardInfos = api.addProduct(productNamePart, productTypePart, pricePart, taxPart)
            emit(Resource.Success(remoteCardInfos))
        } catch(e: HttpException) {
            Log.d("cart", "addProduct: ")
            emit(Resource.Error(
                message = "Oops, something went wrong!"
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection."
            ))
        }
        catch (e:Exception){
            emit(Resource.Error(
                message = e.toString()
            ))
        }


    }

}