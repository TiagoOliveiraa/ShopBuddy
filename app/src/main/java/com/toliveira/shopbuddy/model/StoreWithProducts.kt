package com.toliveira.shopbuddy.model

import androidx.room.Embedded
import androidx.room.Relation

data class StoreWithProducts(
    @Embedded val store: Store,
    @Relation(
        parentColumn = "storeId",
        entityColumn = "productStoreId"
    )
    val products: List<Product>
)
