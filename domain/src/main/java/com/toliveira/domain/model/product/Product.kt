package com.toliveira.domain.model.product

data class Product(
    val productId: Int,
    val productName: String,
    val productQuantity: Int,
    val productPrice: Float,
    val productStoreId: Int?
)
