package com.toliveira.data.store.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "store_table")
data class StoreDTO(
    @PrimaryKey(autoGenerate = true)
    val storeId: Int,
    val storeName: String,
    val storeSpending: Float,
    val storeColor: Int
) : Parcelable
{
    override fun toString(): String {
        return this.storeName
    }
}

