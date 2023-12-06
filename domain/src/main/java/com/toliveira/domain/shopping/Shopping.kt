package com.toliveira.domain.shopping

data class Shopping(
    val storeID: Int,
    val storeName: String,
    val storeSpend: Float,
    val storeColor: Int,
    val productID: Int,
    val productName: String
) {
}