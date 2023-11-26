package com.toliveira.data.store

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@Dao
interface StoreDao {



    @Query("SELECT * FROM store_table")
    fun getAllStores() : Flow<List<Store>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addStore(store: Store)

    @Query("SELECT * FROM store_table WHERE storeName = :storeName")
    fun getStoreInfo(storeName: String): LiveData<Store>

    @Query("UPDATE store_table SET storeSpending = :newValue WHERE storeId = :storeId")
    fun updateStoreSpending(storeId: Int, newValue: Float)

    @Delete
    fun deleteStore(store:Store)

    @Update
    fun updateStore(store : Store)






}