package com.toliveira.shopbuddy.data.product

import androidx.lifecycle.LiveData
import com.toliveira.shopbuddy.model.Product

data class ProductRepository(private val productDao: ProductDao) {

    val getAllProducts: LiveData<List<Product>> = productDao.getAllProducts()

    fun addProduct(product: Product){
        productDao.addProduct(product)
    }

}


