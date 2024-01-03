package com.toliveira.data.product.repository

import com.toliveira.data.product.model.ProductDTO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

data class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    suspend fun getAllProducts(): List<ProductDTO> {
        return productDao.getAllProducts()
            .map { it -> it.map { it }}
            .toList().flatten()
    }

    suspend fun addProduct(product: ProductDTO) {
        productDao.addProduct(product)
    }

    fun updateProduct(product: ProductDTO) {
        productDao.updateProduct(product)
    }

    fun deleteProduct(product: ProductDTO) {
        productDao.deleteProduct(product)
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

    fun getProduct(id: Int): ProductDTO {
        return productDao.getProduct(id)
    }

}


