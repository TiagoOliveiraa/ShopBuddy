package com.toliveira.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toliveira.data.store.Store
import com.toliveira.data.store.StoreDao


@Database(entities = [Store::class],version = 1, exportSchema = false)
abstract class ShopDatabase : RoomDatabase(){

    abstract fun storeDao(): StoreDao

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