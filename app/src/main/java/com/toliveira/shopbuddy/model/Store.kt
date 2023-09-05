package com.toliveira.shopbuddy.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "store_table")
class Store(
    @PrimaryKey(autoGenerate = true)
    val storeId: Int,
    val storeName: String,
    val storeSpending: Float
) : Parcelable