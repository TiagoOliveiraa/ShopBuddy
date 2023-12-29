package com.toliveira.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toliveira.data.product.model.ProductDTO
import com.toliveira.data.product.repository.ProductDao
import com.toliveira.data.store.model.StoreDTO
import com.toliveira.data.store.repository.StoreDao


@Database(entities = [StoreDTO::class,ProductDTO::class],version = 1, exportSchema = false)
abstract class ShopDatabase : RoomDatabase(){

    abstract fun storeDao(): StoreDao
    abstract fun productDao(): ProductDao

    companion object{
        @Volatile
        private var INSTANCE : ShopDatabase? = null

        fun getDatabase(context: Context) : ShopDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
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