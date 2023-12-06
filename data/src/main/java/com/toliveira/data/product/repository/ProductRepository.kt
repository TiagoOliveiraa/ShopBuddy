package com.toliveira.data.product.repository

import androidx.lifecycle.LiveData
import com.toliveira.data.product.mapper.toData
import com.toliveira.data.product.mapper.toDomain
import com.toliveira.data.product.model.ProductDTO
import com.toliveira.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

data class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
            .map { it -> it.map { it.toDomain() }}
            .toList().flatten()
    }

    fun addProduct(product: Product) {
        productDao.addProduct(product.toData())
    }

    fun updateProduct(product: Product) {
        productDao.updateProduct(product.toData())
    }

    fun deleteProduct(product: Product) {
        productDao.deleteProduct(product.toData())
    }

    fun deleteAllProducts() {
        productDao.deleteAllProducts()
    }

    fun deletePurchasedProducts() {
        productDao.deletePurchasedProducts()
    }

    fun clearProduct(id: Int) {
        productDao.clearProduct(id)
    }

    fun getProduct(id: Int): Product {
        return productDao.getProduct(id).toDomain()
    }

}


