package com.toliveira.shopbuddy.data.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.toliveira.shopbuddy.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Query("SELECT * FROM product_table")
    fun getAllProducts() : LiveData<List<Product>>

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Query("DELETE FROM product_table")
    fun deleteAllProducts()

    @Query("DELETE FROM product_table WHERE productStoreId != null")
    fun deletePurchasedProducts()

    @Query("UPDATE product_table SET productStoreID = null WHERE productStoreID = :storeId")
    fun clearProduct(storeId: Int)




}