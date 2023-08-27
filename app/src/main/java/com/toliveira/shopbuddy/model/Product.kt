package com.toliveira.shopbuddy.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val itemName: String,
    val itemQuantity: Int,
    val itemPrice: Float,
    val storeId: Int
)
