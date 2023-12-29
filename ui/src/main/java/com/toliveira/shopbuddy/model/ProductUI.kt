package com.toliveira.shopbuddy.model

data class ProductUI(
    val productId: Int,
    val productName: String,
    val productQuantity: Int,
    val productPrice: Float,
    val productStoreId: Int?
)
