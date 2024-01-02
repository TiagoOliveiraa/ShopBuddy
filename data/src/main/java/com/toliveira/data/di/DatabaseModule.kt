package com.toliveira.data.di

import android.content.Context
import androidx.room.Room
import com.toliveira.data.database.ShopDatabase
import com.toliveira.data.product.repository.ProductDao
import com.toliveira.data.store.repository.StoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideProductDao(database: ShopDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideStoreDao(database: ShopDatabase): StoreDao {
        return database.storeDao()
    }

    @Provides
    @Singleton
    fun provideShopDatabase(@ApplicationContext context: Context): ShopDatabase {
        return Room.databaseBuilder(context, ShopDatabase::class.java,"shopDatabase").build()
    }


}