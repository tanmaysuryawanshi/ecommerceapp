package com.tanmaysuryawanshi.ecommerceapp.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.tanmaysuryawanshi.ecommerceapp.data.local.ProductDatabase
import com.tanmaysuryawanshi.ecommerceapp.data.remote.ApiService
import com.tanmaysuryawanshi.ecommerceapp.data.repository.ProductRepositoryImpl
import com.tanmaysuryawanshi.ecommerceapp.domain.repository.ProductInfoRepository
import com.tanmaysuryawanshi.ecommerceapp.domain.usecases.GetProductsInfo
import com.tanmaysuryawanshi.ecommerceapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProductInfoModule {

    @Provides
    @Singleton
    fun provideGetWorldInfoUseCase(repository:ProductInfoRepository):GetProductsInfo{
        return GetProductsInfo(repository)
    }

    @Provides
    @Singleton
    fun provideProductInfoRepository(
        db:ProductDatabase,
        api:ApiService
    ):ProductInfoRepository{
        return ProductRepositoryImpl(api,db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): ProductDatabase
    {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,"product_db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesApi():ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}