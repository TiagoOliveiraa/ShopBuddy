package com.toliveira.data.product.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.toliveira.data.product.model.ProductDTO
import dagger.Binds
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(productDTO: ProductDTO)

    @Query("SELECT * FROM product_table")
    fun getAllProducts() : Flow<List<ProductDTO>>

    @Update
    fun updateProduct(productDTO: ProductDTO)

    @Delete
    fun deleteProduct(productDTO: ProductDTO)

    @Query("DELETE FROM product_table")
    fun deleteAllProducts()

    @Query("DELETE FROM product_table WHERE productStoreId != null")
    fun deletePurchasedProducts()

    @Query("SELECT * FROM product_table WHERE productId = :id")
    fun getProduct(id: Int) : ProductDTO

    @Query("UPDATE product_table SET productStoreID = null AND productPrice = 0 AND productQuantity = 0 WHERE productStoreID = :storeId")
    fun clearProduct(storeId: Int)




}