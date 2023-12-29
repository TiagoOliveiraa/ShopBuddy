package com.toliveira.domain.model.product.mapper

import com.toliveira.data.product.model.ProductDTO
import com.toliveira.domain.model.product.Product

internal fun ProductDTO.toDomain() : Product{

    return Product(
        productId = this.productId,
        productName = this.productName ?: "",
        productPrice = this.productPrice ?: 0F,
        productQuantity = this.productQuantity ?: 0,
        productStoreId = this.productStoreId
    )

}

internal fun Product.toData() : ProductDTO{

    return ProductDTO(
        productId = this.productId,
        productName = this.productName ?: "",
        productPrice = this.productPrice ?: 0F,
        productQuantity = this.productQuantity ?: 0,
        productStoreId = this.productStoreId
    )

}