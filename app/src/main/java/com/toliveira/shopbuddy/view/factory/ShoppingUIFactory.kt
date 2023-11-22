package com.toliveira.shopbuddy.view.factory

import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.view.model.ShoppingUI
import javax.inject.Inject

class ShoppingUIFactory @Inject constructor() {

    operator fun invoke(stores: List<Store>): ShoppingUI {
        return ShoppingUI(
            stores = stores
        )
    }

}