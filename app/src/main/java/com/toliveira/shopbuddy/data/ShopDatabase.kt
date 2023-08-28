package com.toliveira.shopbuddy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toliveira.shopbuddy.data.product.ProductDao
import com.toliveira.shopbuddy.data.store.StoreDao
import com.toliveira.shopbuddy.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ShopDatabase: RoomDatabase(){

    abstract fun productDao(): ProductDao
    abstract fun storeDao(): StoreDao

    companion object {
        @Volatile
        private var INSTANCE: ShopDatabase? = null

        fun getDatabase(context: Context): ShopDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopDatabase::class.java,
                    "shopDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }

}