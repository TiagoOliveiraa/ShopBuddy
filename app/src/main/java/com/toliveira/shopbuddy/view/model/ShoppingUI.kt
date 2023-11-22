package com.toliveira.shopbuddy.view.model

import com.toliveira.shopbuddy.model.Store

data class ShoppingUI(
    val stores : List<Store>
){

    companion object {
        val EMPTY = ShoppingUI(
            stores = emptyList()
        )
    }

}
