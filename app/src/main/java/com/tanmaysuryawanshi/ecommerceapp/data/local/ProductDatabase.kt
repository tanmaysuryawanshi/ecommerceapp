package com.tanmaysuryawanshi.ecommerceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tanmaysuryawanshi.ecommerceapp.data.local.entity.ProductEntity


@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ProductDatabase: RoomDatabase() {
    abstract val dao:ProductDao
}