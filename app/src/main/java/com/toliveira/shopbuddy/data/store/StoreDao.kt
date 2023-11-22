package com.toliveira.shopbuddy.data.store

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.model.StoreWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {


    @Query("SELECT * FROM store_table")
    fun getAllStores() : Flow<List<Store>>


    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addStore(store:Store)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Query("SELECT * FROM store_table WHERE storeName = :storeName")
    fun getStoreInfo(storeName: String): LiveData<Store>

    @Query("UPDATE store_table SET storeSpending = :newValue WHERE storeId = :storeId")
    fun updateStoreSpending(storeId: Int, newValue: Float)

    @Delete
    fun deleteStore(store:Store)

    @Update
    fun updateStore(store : Store)

    @Transaction
    @Query("SELECT * FROM store_table")
    fun getStoreWithProducts() : List<StoreWithProducts>



}