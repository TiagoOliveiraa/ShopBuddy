package com.toliveira.shopbuddy.shopping.model

import com.toliveira.data.store.Store
import javax.inject.Inject

class ShoppingUIFactory @Inject constructor() {

    operator fun invoke(stores: List<com.toliveira.data.store.Store>): ShoppingUI {
        return ShoppingUI(
            stores = stores
        )
    }

}