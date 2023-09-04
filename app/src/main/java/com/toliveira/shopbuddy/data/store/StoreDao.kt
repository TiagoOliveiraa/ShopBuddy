package com.toliveira.shopbuddy.data.store

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.model.StoreWithProducts

@Dao
interface StoreDao {


    @Query("SELECT * FROM store_table")
    fun getAllStores() : LiveData<List<Store>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addStore(store:Store)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Transaction
    @Query("SELECT * FROM store_table WHERE storeName = :storeName")
    fun getStoreWithProducts(storeName: String): List<StoreWithProducts>



}