package com.toliveira.shopbuddy.model.mapper

import com.toliveira.domain.model.store.Store
import com.toliveira.shopbuddy.model.StoreUI

internal fun Store.toUI() : StoreUI{
    return StoreUI(
        storeId = this.storeId,
        storeName = this.storeName ?: "",
        storeSpending = this.storeSpending ?: 0F,
        storeColor = this.storeColor
    )
}

internal fun StoreUI.toDomain() : Store {
    return Store(
        storeId = this.storeId,
        storeName = this.storeName ?: "",
        storeSpending = this.storeSpending ?: 0F,
        storeColor = this.storeColor
    )
}

