package com.toliveira.domain.model

data class Product(
    val productId: Int,
    val productName: String,
    val productQuantity: Int,
    val productPrice: Float,
    val productStoreId: Int?
)
