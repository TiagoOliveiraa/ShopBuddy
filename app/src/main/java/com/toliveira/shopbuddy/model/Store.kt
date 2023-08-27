package com.toliveira.shopbuddy.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "store_table")
data class Store(
    @PrimaryKey(autoGenerate = true)
    val storeId: Int,
    val storeName: String,
    val storeSpending: Float
)
