package com.toliveira.shopbuddy.model.mapper

import com.toliveira.domain.model.product.Product
import com.toliveira.shopbuddy.model.ProductUI


internal fun Product.toUI(): ProductUI =
    ProductUI(
        productId = this.productId,
        productName = this.productName ?: "",
        productQuantity = this.productQuantity ?: 1,
        productPrice = this.productPrice ?: 0F,
        productStoreId = this.productStoreId
    )

internal fun ProductUI.toUI(): Product =
    Product(
        productId = this.productId,
        productName = this.productName ?: "",
        productQuantity = this.productQuantity ?: 1,
        productPrice = this.productPrice ?: 0F,
        productStoreId = this.productStoreId
    )