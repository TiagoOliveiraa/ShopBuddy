package com.toliveira.shopbuddy.model

import androidx.room.Embedded
import androidx.room.Relation

data class StoreWithProducts(
    @Embedded val store: Store,
    @Relation(
        parentColumn = "storeID",
        entityColumn = "storeId"
    )
    val products: List<Product>
)
