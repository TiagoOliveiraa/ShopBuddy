package com.toliveira.data.store.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.toliveira.data.store.model.StoreDTO
import kotlinx.coroutines.flow.Flow


@Dao
interface StoreDao {



    @Query("SELECT * FROM store_table")
    fun getAllStores() : Flow<List<StoreDTO>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addStore(storeDTO: StoreDTO)

    @Query("SELECT * FROM store_table WHERE storeName = :storeName")
    fun getStoreInfo(storeName: String): StoreDTO

    @Query("UPDATE store_table SET storeSpending = :newValue WHERE storeId = :storeId")
    fun updateStoreSpending(storeId: Int, newValue: Float)

    @Delete
    fun deleteStore(storeDTO: StoreDTO)

    @Update
    fun updateStore(storeDTO : StoreDTO)






}