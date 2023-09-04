package com.toliveira.shopbuddy.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product_table")
class Product(
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val productName: String,
    val productQuantity: Int,
    val productPrice: Float,
    val productStoreId: Int?
) : Parcelable
