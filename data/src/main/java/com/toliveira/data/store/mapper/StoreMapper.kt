package com.toliveira.data.store.mapper

import com.toliveira.data.store.model.StoreDTO
import com.toliveira.domain.model.Store

internal fun StoreDTO.toDomain(): Store {

    return Store(
        storeId = this.storeId,
        storeName = this.storeName ?: "",
        storeSpending = this.storeSpending ?: 0F,
        storeColor = this.storeColor ?: 0
    )

}


internal fun Store.toData(): StoreDTO {

    return StoreDTO(
        storeId = this.storeId,
        storeName = this.storeName ?: "",
        storeSpending = this.storeSpending ?: 0F,
        storeColor = this.storeColor ?: 0
    )

}