package com.tanmaysuryawanshi.ecommerceapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tanmaysuryawanshi.ecommerceapp.data.local.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductInfo(infos:List<ProductEntity>)

    @Query("DELETE FROM productentity WHERE product_name IN(:products)")
    suspend fun deleteWordInfos(products:List<String>)

    @Query("SELECT * FROM productentity WHERE product_name LIKE '%' || :product ||'%'")
    suspend fun getWordInfos(product:String):List<ProductEntity>
}