package com.toliveira.presentation.factory

import com.toliveira.data.store.Store
import com.toliveira.presentation.model.ShoppingUI
import javax.inject.Inject

class ShoppingUIFactory @Inject constructor() {

    operator fun invoke(stores: List<Store>): ShoppingUI {
        return ShoppingUI(
            stores = stores
        )
    }

}